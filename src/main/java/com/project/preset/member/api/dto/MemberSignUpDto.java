package com.project.preset.member.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

public final class MemberSignUpDto {
    // inner class
    @Builder
    public record MemberSignUpRequestDto(
            @Length(min = 3, max = 15) String username,
            @JsonProperty("password")
            @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,100}$")
            String rawPassword,
            @Length(min = 3, max = 15) String nickname,
            @Email String email
    ) {}

    @Builder
    public record MemberSignUpResponseDto(
            // boolean -> false가 default -> false일 땐 포함 안 시킴.
            @JsonInclude(Include.NON_DEFAULT) boolean success,
            // null or ""일 때 포함 안 시킴.
            @JsonInclude(Include.NON_EMPTY) String message
    ) {}
}
