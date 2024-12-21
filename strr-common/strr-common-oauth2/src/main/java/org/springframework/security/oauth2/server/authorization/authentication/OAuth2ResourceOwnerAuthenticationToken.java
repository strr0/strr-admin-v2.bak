package org.springframework.security.oauth2.server.authorization.authentication;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.util.SpringAuthorizationServerVersion;
import org.springframework.util.Assert;

import java.util.*;

public class OAuth2ResourceOwnerAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringAuthorizationServerVersion.SERIAL_VERSION_UID;

    private final AuthorizationGrantType authorizationGrantType;
    private final Authentication clientPrincipal;
    private final String username;
    private final String password;
    private final Set<String> scopes;

    public OAuth2ResourceOwnerAuthenticationToken(AuthorizationGrantType authorizationGrantType, Authentication clientPrincipal,
                                                  @Nullable String username, @Nullable String password, @Nullable Set<String> scopes) {
        super(Collections.emptyList());
        Assert.notNull(authorizationGrantType, "authorizationGrantType cannot be null");
        Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
        this.authorizationGrantType = authorizationGrantType;
        this.clientPrincipal = clientPrincipal;
        this.username = username;
        this.password = password;
        this.scopes = Collections.unmodifiableSet((scopes != null) ? new HashSet<>(scopes) : Collections.emptySet());
    }

    /**
     * Returns the authorization grant type.
     * @return the authorization grant type
     */
    public AuthorizationGrantType getGrantType() {
        return this.authorizationGrantType;
    }

    @Override
    public Object getPrincipal() {
        return this.clientPrincipal;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    /**
     * Returns the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the requested (or authorized) scope(s).
     * @return the requested (or authorized) scope(s), or an empty {@code Set} if not available
     */
    public Set<String> getScopes() {
        return this.scopes;
    }
}
