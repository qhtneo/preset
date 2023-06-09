package com.project.preset.config;

import com.project.preset.properties.cors.CorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CorsProperties corsProperties;

    public WebConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders(corsProperties.exposedHeaders())
                .allowedOrigins(corsProperties.allowed().origins())
                .allowedHeaders(corsProperties.allowed().headers())
                .allowedMethods(corsProperties.allowed().methods());
    }
}
