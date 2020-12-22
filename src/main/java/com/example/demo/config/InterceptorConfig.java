package com.example.demo.config;

import com.example.demo.interceptor.CorsInterceptor;
import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * 不用权限即可访问URL  /api/v1/pub/
 * 要登录即可访问URL   /api/v1/pri/
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor(){
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 拦截全部路径，这个跨域需要放在最上面
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/api/v1/pri/*/*/**")  // 添加拦截路径
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register"); //设置哪些路径不拦截

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
