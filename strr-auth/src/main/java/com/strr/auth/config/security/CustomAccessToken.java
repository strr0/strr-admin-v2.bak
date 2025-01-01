package com.strr.auth.config.security;

import com.strr.auth.constant.AuthConstant;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;

public class CustomAccessToken {
    private String accessToken;
    private String refreshToken;
    private String scope;
    private String tokenType;
    private Long expiresIn;

    public CustomAccessToken(String accessToken, String refreshToken, String scope, String tokenType, Long expiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public static CustomAccessToken convert(OAuth2AccessTokenAuthenticationToken oauth2accessToken) {
        OAuth2AccessToken accessToken = oauth2accessToken.getAccessToken();
        return new CustomAccessToken(accessToken.getTokenValue(), oauth2accessToken.getRefreshToken().getTokenValue(),
                AuthConstant.SCOPE, accessToken.getTokenType().getValue(), AuthConstant.ACCESS_TIMEOUT);
    }
}
