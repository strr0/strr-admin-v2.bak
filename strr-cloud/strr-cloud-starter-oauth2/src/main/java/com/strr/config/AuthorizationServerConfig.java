package com.strr.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.strr.admin.model.SysUserDetails;
import com.strr.config.security.ScopeWithDescription;
import com.strr.jose.Jwks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {
    @Value("${url.gateway:http://127.0.0.1:8000}")
    private String gatewayUrl;
    @Value("${url.web:http://127.0.0.1:8080}")
    private String webUrl;

    /**
     * A Spring Security filter chain for the Protocol Endpoints.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults())	// Enable OpenID Connect 1.0
                .and()
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }

    /**
     * An instance of RegisteredClientRepository for managing clients.
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient gatewayClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("GATEWAY_CLIENT")
                .clientSecret("$2a$10$3Mm.l4wLwZ3Lt7g.T6AtI.794LVvJxGVDw1ddx6pKO3NbZtnpPFoi")   // GATEWAY_SECRET
                .clientAuthenticationMethods(methods -> {
                    methods.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
                    methods.add(ClientAuthenticationMethod.CLIENT_SECRET_POST);
                })
                .authorizationGrantTypes(types -> {
                    types.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                    types.add(AuthorizationGrantType.REFRESH_TOKEN);
                    types.add(AuthorizationGrantType.CLIENT_CREDENTIALS);
                })
                .redirectUri(gatewayUrl + "/login/oauth2/code/gateway-client")
                .scopes(ScopeWithDescription::addScope)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())  // 无需同意页面
                .build();
        RegisteredClient webClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("WEB_CLIENT")
                .clientSecret("$2a$10$dnE/6hRuQHrBNQokKC5qfu/8wmhvuKAMbo8fZm5Ik7V6ZNqYPjKRi")   // WEB_SECRET
                .clientAuthenticationMethods(methods -> {
                    methods.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
                    methods.add(ClientAuthenticationMethod.CLIENT_SECRET_POST);
                })
                .authorizationGrantTypes(types -> {
                    types.add(AuthorizationGrantType.AUTHORIZATION_CODE);
                    types.add(AuthorizationGrantType.REFRESH_TOKEN);
                    types.add(AuthorizationGrantType.CLIENT_CREDENTIALS);
                })
                .redirectUri(webUrl)
                .scopes(ScopeWithDescription::addScope)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())  // 无需同意页面
                .build();
        return new InMemoryRegisteredClientRepository(gatewayClient, webClient);
    }

    /**
     * An instance of com.nimbusds.jose.jwk.source.JWKSource for signing access tokens.
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    /**
     * An instance of JwtDecoder for decoding signed access tokens.
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * An instance of AuthorizationServerSettings to configure Spring Authorization Server.
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    /**
     * 自定义jwt
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer() {
        return context -> {
            if (context.getPrincipal().getPrincipal() instanceof SysUserDetails user) {
                JwtClaimsSet.Builder claims = context.getClaims();
                claims.claim("user_id", user.getId());
            }
        };
    }
}
