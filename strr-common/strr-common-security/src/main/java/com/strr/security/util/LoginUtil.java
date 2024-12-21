package com.strr.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

/**
 * 登录工具
 */
public class LoginUtil {
    /**
     * 获取登录用户id
     */
    public static Integer getLoginUserId() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(item -> item instanceof Jwt jwt ? jwt : null)
                .map(Jwt::getClaims).map(map -> (Long) map.get("userId")).map(Long::intValue)
                .orElse(null);
    }
}
