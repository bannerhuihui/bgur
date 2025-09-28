package com.bgur.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

/**
 * @projectName: bgur
 * @package: com.bgur.util
 * @className: JwtUtil
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/15 13:44
 * @version: 1.0
 */
public class JwtUtil {
    // 建议放到配置文件
    private static final String SECRET = "BgurJwtSecretKey_2024!@#_A1B2C3D4E5F6G7H8I9J0KLMNOPQRSTU"; // 32位以上
    private static final long ACCESS_TOKEN_EXPIRE = 30 * 60 * 1000; // 30分钟
    private static final long REFRESH_TOKEN_EXPIRE = 7 * 24 * 60 * 60 * 1000; // 7天

    private static SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // 生成AccessToken
    public static String generateAccessToken(Integer userId, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE))
                .signWith(getKey())
                .compact();
    }

    // 生成RefreshToken
    public static String generateRefreshToken(Integer userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE))
                .signWith(getKey())
                .compact();
    }

    // 解析和校验Token
    public static Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 新增：从token中获取userId（Integer类型）
    public static Integer getUserIdFromToken(String token) throws JwtException {
        Claims claims = parseToken(token);
        return Integer.valueOf(claims.getSubject());
    }
}
