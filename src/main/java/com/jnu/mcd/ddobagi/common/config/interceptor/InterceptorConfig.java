package com.jnu.mcd.ddobagi.common.config.interceptor;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AuthInfoInterceptor authInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( authInfoInterceptor )
                .addPathPatterns( "/**" )
                .excludePathPatterns("/api/v1/auth/**", "/view/**", "/images/**", "/css/**", "/static/**", "/js/**", "/");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
