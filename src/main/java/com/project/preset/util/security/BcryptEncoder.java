package com.project.preset.util.security;

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
        // 기존: {bcrypt} 생략 가능 -> 기본값 같은 취급.
        return "{bcrypt}" + super.encode(rawPassword);
    }
}
