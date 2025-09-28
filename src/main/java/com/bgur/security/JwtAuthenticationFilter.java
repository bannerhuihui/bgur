package com.bgur.security;

import cn.hutool.core.util.StrUtil;
import com.bgur.bean.LoginUser;
import com.bgur.service.LoginUserService;
import com.bgur.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: bgur
 * @package: com.bgur.security
 * @className: JwtAuthenticationFilter
 * @author: huihui
 * @description: TODO
 * @date: 2025/7/15 13:56
 * @version: 1.0
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private LoginUserService loginUserService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (StrUtil.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
            // 没有 token，不做任何处理，直接放行
            chain.doFilter(request, response);
            return;
        }
        //需要认证逻辑
        String token = authHeader.substring(7);
        try {
            // 2. 校验并解析token
            Claims claims = JwtUtil.parseToken(token);
            String userId = claims.getSubject();
            // 3. 如果当前上下文没有认证信息，则查库加载用户信息
            if (StrUtil.isNotEmpty(userId) && SecurityContextHolder.getContext().getAuthentication() == null) {
                LoginUser userDetails = loginUserService.loadLoginUser(userId);
                if (userDetails == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write("{\"code\":401,\"message\":\"用户不存在或已被禁用\"}");
                    return;
                }
                // 4. 构造认证对象，注入上下文
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // 5. 放行
            chain.doFilter(request, response);
        } catch (JwtException e) {
            // token无效或过期，返回401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"Token无效或已过期\"}");
        }
    }
}
