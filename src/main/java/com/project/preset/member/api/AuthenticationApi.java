package com.project.preset.member.api;

import com.project.preset.member.service.MemberSignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.project.preset.member.api.dto.AuthenticationDto.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationApi {

    private final MemberSignInService memberSignInService;

    @PostMapping("/sign-in")
    public SignInResponseDto signIn(@RequestBody @Valid SignInRequestDto body) {
        String token = memberSignInService.authenticateWithJwt(body.username(), body.rawPassword());
        return SignInResponseDto.builder()
                .token(token)
                .build();
    }
}
