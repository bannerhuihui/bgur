package com.bgur.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: bgur
 * @package: com.bgur.util
 * @className: RequestUtil
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/16 12:43
 * @version: 1.0
 */
public class RequestUtil {

    public static String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多级代理时，取第一个IP
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        } else {
            ip = request.getHeader("Proxy-Client-IP");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            }
        }
        // 兼容IPv6本地回环
        if ("0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }
}
