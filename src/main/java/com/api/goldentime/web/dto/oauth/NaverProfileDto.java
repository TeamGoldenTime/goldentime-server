package com.api.goldentime.web.dto.oauth;

import com.api.goldentime.domain.user.OauthProvider;
import com.api.goldentime.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NaverProfileDto {

  private String resultcode;
  private String message;
  private NaverProfileResponse response;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class NaverProfileResponse {

    private String id;
    private String name;
    private String email;
    private String mobile;
    private String gender;
  }

  public User toUserEntity() {
    return User.builder()
        .identifier(response.id)
        .name(response.name)
        .email(response.email)
        .phoneNumber(response.mobile)
        .gender(response.gender)
        .provider(OauthProvider.NAVER)
        .build();
  }
}
