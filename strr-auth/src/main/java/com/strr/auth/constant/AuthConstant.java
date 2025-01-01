package com.strr.auth.constant;

public interface AuthConstant {
    Long ACCESS_TIMEOUT = 3600L;
    Long REFRESH_TIMEOUT = 604800L;

    String CLIENT_ID = "WEB_CLIENT";
    String CLIENT_SECRET = "$2a$10$dnE/6hRuQHrBNQokKC5qfu/8wmhvuKAMbo8fZm5Ik7V6ZNqYPjKRi";   // WEB_SECRET
    String REDIRECT_URI = "http://127.0.0.1:8080/login/oauth2/code/default-client";
    String SCOPE = "web";
    String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
}
