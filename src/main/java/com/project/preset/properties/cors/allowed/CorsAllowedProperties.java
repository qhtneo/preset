package com.project.preset.properties.cors.allowed;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
public record CorsAllowedProperties(String[] methods, String[] headers, String[] origins) {
}
