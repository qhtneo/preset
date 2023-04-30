package com.project.preset.member.service;

public interface MemberSignInService {
    // ...

    String authenticateWithJwt(String username, String rawPassword);
}
