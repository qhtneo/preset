package com.project.preset.member.service;

import com.project.preset.util.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMemberSignInService implements MemberSignInService {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public String authenticateWithJwt(String username, String rawPassword) {
        // [1] 인증
        UsernamePasswordAuthenticationToken authInfo =
                new UsernamePasswordAuthenticationToken(username, rawPassword);

        authenticationManager.authenticate(authInfo); // 인증 안 되면 -> Exception 발생

        System.out.println("Here?");
        // [2] 토큰 발급 (여기 왔다는 거 자체가 통과된 거니까) JWT로 액세스 토큰 발급
        return jwtProvider.generate(username);
    }
}
