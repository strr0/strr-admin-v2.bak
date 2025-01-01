package com.strr.auth.config.security;

import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContext;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;

public class CustomAuthorizationServerContext implements AuthorizationServerContext {
    private final String issuer;
    private final AuthorizationServerSettings authorizationServerSettings;

    public CustomAuthorizationServerContext(String issuer, AuthorizationServerSettings authorizationServerSettings) {
        this.issuer = issuer;
        this.authorizationServerSettings = authorizationServerSettings;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public AuthorizationServerSettings getAuthorizationServerSettings() {
        return authorizationServerSettings;
    }
}
