package com.project.preset.util.security.encoder;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
public final class BcryptEncoder extends BCryptPasswordEncoder {

    public BcryptEncoder(int costFactor) {
        super(costFactor);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        // Authentication Manager -> {bcrypt}, {scrypt}, {pbkdf2}, {noop}(평문) 등등 지원
        // 아마도 시큐리티 버전에 따라서 {bcrypt}는 명시 여부 다른 듯함.
        // (이번 버전에는 안 붙이는 게 맞는 듯함.)
        return "{bcrypt}" + super.encode(rawPassword);
    }
}
