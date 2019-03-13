package com.gatewayadmin.config;

import com.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springclouddemo
 * @description:
 * @author: liu liang
 * @create: 2019-03-13 17:28
 */
@Configuration
public class WebMvcConfigurationAdvice implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor userInterceptor(){
        return  new UserInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor());
    }
}
