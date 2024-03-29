package com.ujacha.tune.member.domain;

import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.test.domain.TestEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String uid;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String nickname;
    private String reliableUid;
    private Integer childAge;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestEntity> test = new ArrayList<>();


    public static Member toEntity(final MemberRequestDTO.KakaoLogin dto) {
        return Member.builder()
                .uid(dto.getUid())
                .email(dto.getEmail())
                .build();
    }

    public void firstLoginCategory(final MemberRequestDTO.FirstLogin dto) {
        this.nickname = dto.getNickname();
        this.childAge = dto.getChildAge();
    }

    public void updateMember(final MemberRequestDTO.MemberUpdate dto,String reliableUid) {
        this.nickname = dto.getNickname();
        this.childAge = dto.getChildAge();
        this.reliableUid = reliableUid;
    }
    public void updateMember(final Integer childAge,String reliableUid) {
        this.childAge = childAge;
        this.reliableUid = reliableUid;
    }

    public void setReliableUidToNull() {
        this.reliableUid = null;
    }
}
