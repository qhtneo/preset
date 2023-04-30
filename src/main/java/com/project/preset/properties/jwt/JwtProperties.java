package com.project.preset.properties.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties("app.security.jwt")
@ConfigurationPropertiesBinding
public record JwtProperties(
        String secret,
        Long expiration
) {
    // new JwtProperties(null, null);
    public JwtProperties {
        System.out.println("secret 내용: " + secret);
        if (secret == null) secret = "SAMPLE_SECRET_KEY";
        if (expiration == null) expiration = 1_800_000L;
        // this.secret -> 아님.
        // secret -> 이거임.
        // this.속성 쓰면 안 된다고 생각하세욧!
    }
}
