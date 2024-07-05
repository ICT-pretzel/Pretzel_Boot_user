package com.ict.pretzel.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ict.pretzel.jwt.JwtInterceptor;

public class IntercepterConfig implements WebMvcConfigurer{

    /* private final JwtInterceptor jwtInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("인터셉터 등록");
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/oauth2/**", "/user/login", "/user/logout");
    } */

}
