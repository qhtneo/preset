package com.project.preset.properties.cors;

import com.project.preset.properties.cors.allowed.CorsAllowedProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

// toString, getter, 전체 받는 생성자(AllArgsConstructor), ...
// 불변성: 속성들이 다 final이 됨.
@ConfigurationProperties("app.security.cors")
@ConfigurationPropertiesBinding
public record CorsProperties(String exposedHeaders, @NestedConfigurationProperty CorsAllowedProperties allowed) {

}
