package com.project.preset.util.security.encoder;

import com.project.preset.properties.password.PasswordEncoderProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

import static org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;

@Component
@RequiredArgsConstructor
public final class PasswordEncoderFactory {
    private final PasswordEncoderProperties passwordEncoderProperties;

    public enum EncoderType {
        BCRYPT("{bcrypt}"),
        PBKDF2("{pbkdf2}"),
        SCRYPT("{scrypt}");

        public final String PREFIX;

        EncoderType(String prefix) {
            assert Arrays.stream(values()).noneMatch(item -> Objects.equals(item.PREFIX, prefix))
                    : "Prefix 안 겹치게 작성";

            this.PREFIX = prefix;
        }

        public boolean hasPrefixOf(String prefix) {
            return Objects.equals(prefix, this.PREFIX);
        }
    }

    public PasswordEncoder defaultEncoder() {
        return getEncoder(passwordEncoderProperties.defaultEncoder());
    }

    public PasswordEncoder getEncoder(@NonNull EncoderType encoder) {
        // Java 12 -> inline switch~case
        return switch(encoder) {
            case BCRYPT -> createBcrypt();
            case PBKDF2 -> createScrypt();
            case SCRYPT -> createPbkdf2();
        };
    }

    private BCryptPasswordEncoder createBcrypt() {
        int costFactor = passwordEncoderProperties.bcrypt().costFactor();
        return new BCryptPasswordEncoder(costFactor);
    }

    private SCryptPasswordEncoder createScrypt() {
        // 서버 환경의 스펙 입력 -> 비밀번호 해싱 등 연산을 약 1초 정도에 최대한 가깝게 만들어 줌. -> 메모리 사용량 등 리스크 있지만
        // 장점: 메모리 사용량도 늘리기 때문에 -> GPU 연산 통한 공격 등등... 좀 더 면역
        int cpuCost = passwordEncoderProperties.scrypt().cpuCost();
        int memoryCost = passwordEncoderProperties.scrypt().memoryCost();
        int parallelism = passwordEncoderProperties.scrypt().parallelism();
        int keyLength = passwordEncoderProperties.scrypt().keyLength();
        int saltLength = passwordEncoderProperties.scrypt().saltLength();
        return new SCryptPasswordEncoder(cpuCost, memoryCost, parallelism, keyLength, saltLength);
    }

    private Pbkdf2PasswordEncoder createPbkdf2() {
        // Nist에서 공인된 해싱 알고리즘들 사용하기 좋음. -> 서류 작업 등에선 참 좋음(어디에 보안 수준 입증해야 할 때).
        // 20 -> salt Length
        // 12 -> cost factor(반복해싱 횟수)
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("key-unloaded", 20, 12);
        encoder.setAlgorithm(SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
        return encoder;
    }
}
