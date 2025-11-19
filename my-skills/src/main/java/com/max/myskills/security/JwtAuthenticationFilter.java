package com.max.myskills.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 获取请求路径
        String requestURI = request.getRequestURI();

        // 如果是登录或注册接口，或者其他公开的GET请求，直接放行
        if (requestURI.startsWith("/api/user/") ||
                (request.getMethod().equalsIgnoreCase("GET") && requestURI.startsWith("/api/articles/"))) {
            filterChain.doFilter(request, response);
            return; // 直接返回，不执行后续的Token验证逻辑
        }
        // 1. 从请求中获取 JWT
        String jwt = getJwtFromRequest(request);

        // 2. 验证 Token
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            // 3. 从 Token 中获取用户名
            String username = tokenProvider.getUsernameFromJWT(jwt);

            // 4. 加载用户信息 (UserDetails)
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 5. 创建认证对象
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 6. 将认证信息设置到 Spring Security 的上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else{

        }

        // 7. 继续执行过滤器链中的下一个过滤器
        filterChain.doFilter(request, response);
    }

    // 从请求头中提取 Token
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}