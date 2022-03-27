package com.api.goldentime.domain.user;

import com.api.goldentime.web.dto.NaverProfileDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String gender;

    @Builder
    public User(Long id, String email, String name, String phoneNumber, String gender) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public static User create(NaverProfile naverProfile) {
        return User.builder()
            .email(naverProfile.getEmail())
            .name(naverProfile.getName())
            .phoneNumber(naverProfile.getMobile())
            .gender(naverProfile.getGender())
            .build();
    }
}
