package com.project.preset.member.api;
import com.project.preset.member.domain.Member;
import com.project.preset.member.service.MemberCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.project.preset.member.api.dto.MemberSignUpDto.*;

@RestController
@RequestMapping("/members")
public class MemberCommandApi {
    private final MemberCommandService memberCommandService;
    
    public MemberCommandApi(MemberCommandService memberCommandService) {
        this.memberCommandService = memberCommandService;
    }

    @PostMapping("/sign-up")
    public MemberSignUpResponseDto signUp(@RequestBody @Valid MemberSignUpRequestDto body) {
        Member member = memberCommandService.signUp(body);
        boolean success = member != null;
        
        return MemberSignUpResponseDto.builder()
                .success(success)
                .message(success ? "회원가입 성공" : "회원가입 실패") // 원래는 Exception 던짐
                .build();
    }
}
