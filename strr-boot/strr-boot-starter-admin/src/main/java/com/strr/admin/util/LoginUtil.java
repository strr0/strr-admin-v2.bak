package com.strr.admin.util;

import com.strr.admin.model.SysUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
                .map(item -> item instanceof SysUserDetails ? (SysUserDetails) item : null)
                .map(SysUserDetails::getId)
                .orElse(null);
    }
}
