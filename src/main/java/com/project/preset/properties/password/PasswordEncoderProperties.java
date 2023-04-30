package com.project.preset.properties.password;

import com.project.preset.properties.password.bcrypt.BcryptProperties;
import com.project.preset.properties.password.scrypt.ScryptProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import static com.project.preset.util.security.encoder.PasswordEncoderFactory.EncoderType;

@ConfigurationProperties("app.security.password")
public record PasswordEncoderProperties(
        EncoderType defaultEncoder,
        @NestedConfigurationProperty BcryptProperties bcrypt,
        @NestedConfigurationProperty ScryptProperties scrypt
        ) {
    public PasswordEncoderProperties {
        if (defaultEncoder == null) defaultEncoder = EncoderType.BCRYPT;
    }
}
