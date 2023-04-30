package com.project.preset.member.repository;

import com.project.preset.member.domain.Member;
import com.project.preset.member.repository.projection.MemberPasswordProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<MemberPasswordProjection> findPasswordByUsername(String username);
//    Optional<Member> findByNicknameAndUsername(String nickname, String username);
}
