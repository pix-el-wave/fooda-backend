package inha.capstone.fooda.domain.member.entity;

import inha.capstone.fooda.domain.common.entity.BaseEntity;
import inha.capstone.fooda.domain.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer weight;

    private Integer height;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Integer targetWeight;

    private Integer targetKcal;

    @Builder
    public Member(Long id, String name, String username, String password, Gender gender, Integer weight, Integer height, Integer age, Role role, Integer targetWeight, Integer targetKcal) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.role = role;
        this.targetWeight = targetWeight;
        this.targetKcal = targetKcal;
    }

    /**
     * memberDto에 따라 엔티티의 정보를 수정한다.
     *
     * @param memberDto 수정하려는 정보가 담긴 memberDto
     */
    public void update(MemberDto memberDto) {
        this.height = memberDto.getHeight();
        this.weight = memberDto.getWeight();
        this.age = memberDto.getAge();
        this.targetWeight = memberDto.getTargetWeight();
        this.targetKcal = memberDto.getTargetKcal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member that = (Member) o;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
