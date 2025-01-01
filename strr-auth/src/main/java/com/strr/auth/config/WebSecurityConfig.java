package com.strr.auth.config;

import com.strr.auth.config.security.CustomAccessToken;
import com.strr.auth.config.security.CustomAuthorizationServerContext;
import com.strr.auth.constant.AuthConstant;
import com.strr.auth.mapper.SysUserMapper;
import com.strr.auth.model.SysUserDetails;
import com.strr.auth.util.ResponseUtil;
import com.strr.base.model.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContext;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Configuration
public class WebSecurityConfig {
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    private final OAuth2AuthorizationService authorizationService;
    private final RegisteredClientRepository registeredClientRepository;
    private final AuthorizationServerSettings authorizationServerSettings;

    public WebSecurityConfig(OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, OAuth2AuthorizationService authorizationService,
                             RegisteredClientRepository registeredClientRepository, AuthorizationServerSettings authorizationServerSettings) {
        this.tokenGenerator = tokenGenerator;
        this.authorizationService = authorizationService;
        this.registeredClientRepository = registeredClientRepository;
        this.authorizationServerSettings = authorizationServerSettings;
    }

    /**
     * A Spring Security filter chain for authentication.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 登录
                .successHandler((request, response, authentication) -> {
                    OAuth2AccessTokenAuthenticationToken oauth2accessToken = getAccessToken(request, authentication);
                    ResponseUtil.write(response, CustomAccessToken.convert(oauth2accessToken));
                })
                .failureHandler((request, response, exception) -> {
                    ResponseUtil.write(response, Result.error());
                })
                //退出
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    ResponseUtil.write(response, Result.ok());
                })
                // 禁用csrf
                .and()
                .csrf().disable()
                .exceptionHandling();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(SysUserMapper sysUserMapper) {
        return username -> {
            List<SysUserDetails> userDetailsList = sysUserMapper.getByUsername(username);
            if (!userDetailsList.isEmpty()) {
                return userDetailsList.get(0);
            }
            throw new UsernameNotFoundException(String.format("User '%s' not found.", username));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private OAuth2AccessTokenAuthenticationToken getAccessToken(HttpServletRequest request, Authentication authentication) {
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(AuthConstant.CLIENT_ID);
        // tokenContext
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(authentication)
                .authorizationServerContext(getAuthorizationServerContext(request))
                .authorizedScopes(Collections.singleton(AuthConstant.SCOPE))
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizationGrant(authentication);
        OAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                    "The token generator failed to generate the access token.", AuthConstant.ERROR_URI);
            throw new OAuth2AuthenticationException(error);
        }
        // accessToken
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
                generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(authentication.getName())
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizedScopes(Collections.singleton(AuthConstant.SCOPE))
                .attribute(Principal.class.getName(), authentication);
        if (generatedAccessToken instanceof ClaimAccessor claimAccessor) {
            authorizationBuilder.token(accessToken, metadata -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, claimAccessor.getClaims()));
        } else {
            authorizationBuilder.accessToken(accessToken);
        }
        // refreshToken
        OAuth2RefreshToken refreshToken = null;
        tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
        OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
        if (generatedRefreshToken != null) {
            if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                        "The token generator failed to generate a valid refresh token.", AuthConstant.ERROR_URI);
                throw new OAuth2AuthenticationException(error);
            }
            refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
            authorizationBuilder.refreshToken(refreshToken);
        }
        OAuth2Authorization authorization = authorizationBuilder.build();
        this.authorizationService.save(authorization);
        return new OAuth2AccessTokenAuthenticationToken(registeredClient, authentication, accessToken, refreshToken, Collections.emptyMap());
    }

    private AuthorizationServerContext getAuthorizationServerContext(HttpServletRequest request) {
        return new CustomAuthorizationServerContext(resolveIssuer(this.authorizationServerSettings, request), this.authorizationServerSettings);
    }

    private static String resolveIssuer(AuthorizationServerSettings authorizationServerSettings, HttpServletRequest request) {
        return authorizationServerSettings.getIssuer() != null ? authorizationServerSettings.getIssuer() : getContextPath(request);
    }

    private static String getContextPath(HttpServletRequest request) {
        return UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request)).replacePath(request.getContextPath()).replaceQuery(null).fragment(null).build().toUriString();
    }
}
