package com.project.preset.member.service;

import com.project.preset.member.api.dto.MemberSignUpDto;
import com.project.preset.member.domain.Member;
import com.project.preset.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@Primary
//@RequiredArgsConstructor
public final class DefaultMemberCommandService implements MemberCommandService {
    // MemberCommandService a; // 단점: 오타 내면 ㅈ 됨.
    // MemberCommandService abcMemberCommandService; // 원래는 클래스 이름을 lower camelCase로만 바꾸면 매칭.
    // MemberCommandService defMemberCommandService;
    // MemberCommandService ghiMemberCommandService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultMemberCommandService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member signUp(MemberSignUpDto.MemberSignUpRequestDto dto) {
        String password = passwordEncoder.encode(dto.rawPassword());
        Member joinMember = Member.builder()
                .username(dto.username())
                .password(password)
                .nickname(dto.nickname())
                .email(dto.email())
                .build();

        return memberRepository.save(joinMember);
    }
}
