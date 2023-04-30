package com.project.preset.member.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

public final class AuthenticationDto {
    @Builder
    public record SignInRequestDto(
            @NotEmpty String username,
            @JsonProperty("password") String rawPassword
    ) {}

    @Builder
    public record SignInResponseDto(
//            @JsonInclude(Include.NON_DEFAULT) boolean requiresMfa,
            @JsonInclude(Include.NON_EMPTY) String token
    ) {}
}
