package inha.capstone.fooda.domain.member.dto;

import inha.capstone.fooda.domain.member.entity.Gender;
import inha.capstone.fooda.domain.member.entity.Member;
import inha.capstone.fooda.domain.member.entity.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberDto {
    private Long id;
    private String name;
    private String username;
    @Setter
    private String password;
    private Gender gender;
    private Integer weight;
    private Integer height;
    private Integer age;
    private Integer targetWeight;
    private Integer targetKcal;
    @Setter
    private Role role;
    private String kakaoEmail;

    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getUsername(),
                member.getPassword(),
                member.getGender(),
                member.getWeight(),
                member.getHeight(),
                member.getAge(),
                member.getTargetWeight(),
                member.getTargetKcal(),
                member.getRole(),
                member.getKakaoEmail()
        );
    }

    public static MemberDto of(String name, String username, String password, Gender gender, Integer weight,
                               Integer height, Integer age, Integer targetWeight, Integer targetKcal) {
        return new MemberDto(null, name, username, password, gender, weight, height, age, targetWeight, targetKcal,
                null, null);
    }

    public static MemberDto of(String name, String username, String password, Gender gender, Integer weight,
                               Integer height, Integer age, Integer targetWeight, Integer targetKcal,
                               String kakaoEmail) {
        return new MemberDto(null, name, username, password, gender, weight, height, age, targetWeight, targetKcal,
                null, kakaoEmail);
    }

    public static MemberDto of(Integer weight, Integer height, Integer age, Integer targetWeight, Integer targetKcal) {
        return new MemberDto(null, null, null, null, null, weight, height, age, targetWeight, targetKcal, null, null);
    }

    public static MemberDto of(String username, String password) {
        return new MemberDto(null, username, username, password, null, null, null, null, null, null, null, null);
    }

    public Member toEntity() {
        return Member.builder()
                .id(getId())
                .name(getName())
                .username(getUsername())
                .password(getPassword())
                .gender(getGender())
                .weight(getWeight())
                .height(getHeight())
                .targetWeight(getTargetWeight())
                .targetKcal(getTargetKcal())
                .age(getAge())
                .role(getRole())
                .kakaoEmail(getKakaoEmail())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberDto)) {
            return false;
        }
        MemberDto that = (MemberDto) o;
        return this.getId() != null && this.getId().equals(that.getId());
    }
}
