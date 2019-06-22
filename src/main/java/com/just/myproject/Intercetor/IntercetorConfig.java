package com.just.myproject.Intercetor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercetorConfig implements WebMvcConfigurer {
    @Override
//
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OnlineIntercepter()).addPathPatterns("/app/v1/record/online/*/**","/app/v1/user/online/*/**");
        registry.addInterceptor(new AdminIntercepter()).addPathPatterns("/app/v1/admin/*/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
