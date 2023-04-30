package com.project.preset.member.repository;

import com.project.preset.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    List<Member> findByUsername(String username);
//    Optional<Member> findByNicknameAndUsername(String nickname, String username);
}
