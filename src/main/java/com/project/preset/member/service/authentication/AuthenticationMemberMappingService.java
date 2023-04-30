package com.project.preset.member.service.authentication;

import com.project.preset.member.repository.MemberRepository;
import com.project.preset.member.repository.projection.MemberPasswordProjection;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Authentication Manager 빈 등록
// -> 얘가 가져다 쓸 ~ implements UserDetailsService라는 서비스 구현 클래스.

@Service
public class AuthenticationMemberMappingService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public AuthenticationMemberMappingService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberPasswordProjection> optionalMember = memberRepository.findPasswordByUsername(username);

        if (optionalMember.isEmpty()) throw new UsernameNotFoundException("인증 정보 불일치");

        MemberPasswordProjection member =  optionalMember.get();

        return User
                .withUsername(username)
                .password(member.password())
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
