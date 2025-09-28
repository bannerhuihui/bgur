package com.bgur.util;

import com.bgur.bean.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @projectName: bgur
 * @package: com.bgur.util
 * @className: SecurityUtils
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/17 10:31
 * @version: 1.0
 */
public class SecurityUtils {

    /**
     * 获取当前登录用户的ID
     */
    public static Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            return ((LoginUser) principal).getId();
        }
        // 其他情况可根据实际项目扩展
        return null;
    }

    /**
     * 获取当前登录用户对象
     */
    public static LoginUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            return (LoginUser) principal;
        }
        return null;
    }

    /**
     * 获取当前用户名
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            return ((LoginUser) principal).getUsername();
        }
        if (principal instanceof String) {
            return (String) principal;
        }
        return null;
    }
}
