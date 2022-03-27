package com.api.goldentime.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NaverProfile {
  private final String id;
  private final String nickname;
  private final String name;
  private final String email;
  private final String gender;
  private final String age;
  private final String mobile;

  @Builder
  public NaverProfile(String id, String nickname, String name, String email, String gender,
      String age, String mobile) {
    this.id = id;
    this.nickname = nickname;
    this.name = name;
    this.email = email;
    this.gender = gender;
    this.age = age;
    this.mobile = mobile;
  }
}
