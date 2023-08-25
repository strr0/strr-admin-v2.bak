package com.strr.admin.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

public class CustomSysUtil {
    /**
     * 获取登录用户id
     */
    public static Integer getLoginUserId() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(item -> item instanceof Jwt ? (Jwt) item : null)
                .map(Jwt::getClaims).map(map -> (Long) map.get("user_id")).map(Long::intValue)
                .orElse(null);
    }
}
