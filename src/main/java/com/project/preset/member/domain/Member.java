package com.project.preset.member.domain;

import com.project.preset.member.domain.type.MemberStatus;
import com.project.preset.member.domain.type.RoleType;
import com.project.preset.support.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(
        name = "member"
)
public class Member extends BaseEntity {
    @Column private String username;
    @Column private String password;
    @Column private String nickname;
    @Column private String email;
    @Column
    @Builder.Default
    LocalDateTime regDate = LocalDateTime.now();
    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    MemberStatus status = MemberStatus.PENDING;
    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    RoleType roleName = RoleType.USER_ROLE;
}
