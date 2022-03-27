package com.api.goldentime.web.dto;

import com.api.goldentime.domain.user.NaverProfile;
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

  public NaverProfile toEntity() {
    return NaverProfile.builder()
        .id(response.id)
        .name(response.name)
        .email(response.email)
        .mobile(response.mobile)
        .gender(response.gender)
        .build();
  }
}
