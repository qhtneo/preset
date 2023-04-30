package com.project.preset.member.service;

import com.project.preset.member.api.dto.MemberSignUpDto;
import com.project.preset.member.domain.Member;

public interface MemberCommandService {
    Member signUp(MemberSignUpDto.MemberSignUpRequestDto dto);
}
