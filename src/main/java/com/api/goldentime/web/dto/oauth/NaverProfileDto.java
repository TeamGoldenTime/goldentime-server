package com.api.goldentime.web.dto.oauth;

import com.api.goldentime.domain.user.OauthProvider;
import com.api.goldentime.domain.user.User;
import lombok.Getter;

@Getter
public class NaverProfileDto {

  private String resultcode;
  private String message;
  private NaverProfileResponse response;

  @Getter
  public static class NaverProfileResponse {

    private String id;
    private String name;
    private String email;
    private String mobile;
    private String gender;
  }

  public User toUserEntity() {
    return User.builder()
        .name(response.name)
        .email(response.email)
        .phoneNumber(response.mobile)
        .gender(response.gender)
        .provider(OauthProvider.NAVER)
        .build();
  }
}
