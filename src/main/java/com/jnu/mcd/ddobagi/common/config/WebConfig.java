package com.jnu.mcd.ddobagi.common.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final long MAX_AGE_SECS = 3600;
    private final List<String> allowOriginUrlPatterns;
    public static final String ALLOWED_METHOD_NAMES =
            "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";




    public WebConfig(
            @Value("${cors.allow-origin.urls}") final String allowOriginUrlPatterns) {
        this.allowOriginUrlPatterns =
                Arrays.stream(allowOriginUrlPatterns.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] patterns = allowOriginUrlPatterns.toArray(String[]::new);

        registry.addMapping("/**")
                .allowedOriginPatterns(patterns)
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","))
                .exposedHeaders("Authorization", "Set-Cookie")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String css = "/css/**";

        registry.addResourceHandler(css)
                .addResourceLocations("classpath:/static/css/");

        String js = "/js/**";
        registry.addResourceHandler(js)
                .addResourceLocations("classpath:/static/js/");

        String html = "/html/**";
        registry.addResourceHandler(html)
                .addResourceLocations("classpath:/static/html/");

        String images = "/images/**";
        registry.addResourceHandler(images)
                .addResourceLocations("classpath:/static/images/");
    }
}
