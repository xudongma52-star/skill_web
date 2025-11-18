

package com.max.myskills.config;

import com.max.myskills.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 关闭 CSRF 保护，因为我们使用 JWT，不需要 session
                .csrf(csrf -> csrf.disable())

                // 2. 设置 Session 管理策略为 STATELESS (无状态)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. 配置请求授权规则
                .authorizeHttpRequests(auth -> auth
                        // 允许所有人访问 /api/user/** 下的接口 (登录和注册)
                        .requestMatchers("/api/user/**").permitAll()
                        // 允许所有人用 GET 方法访问 /api/articles/** (查看文章列表和详情)
                        .requestMatchers(HttpMethod.GET, "/api/articles/**").permitAll()
                        // 除了上面允许的，其他所有请求都需要认证
                        .anyRequest().authenticated()
                );

        // 4. 将我们的 JWT 过滤器添加到 Spring Security 的过滤器链中
        // 在 UsernamePasswordAuthenticationFilter 之前执行
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}