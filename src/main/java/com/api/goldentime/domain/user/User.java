package com.api.goldentime.domain.user;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class User {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String gender;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    @Builder
    public User(String identifier, String email, String name, String phoneNumber, String gender, OauthProvider provider) {
        if (identifier == null) {
            throw new IllegalStateException("User Entity 고유 식별자가 있어야 합니다.");
        }

        this.identifier = identifier;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.provider = provider;
    }
}
