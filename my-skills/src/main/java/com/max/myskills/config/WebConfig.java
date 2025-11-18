package com.max.myskills.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 对所有API路径生效
                .allowedOrigins("http://localhost:5173") // 只允许你的前端源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                .allowCredentials(true) // 允许携带 Cookie
                .maxAge(3600) // 预检请求的有效期
                .allowedHeaders("*"); // 允许所有的请求头
    }
}