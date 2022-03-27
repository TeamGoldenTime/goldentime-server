package com.api.goldentime.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NaverLoginRequestDto {

  @NotBlank
  private String accessToken;
  private String expireAt;
  private String refreshToken;
  private String tokenType;
}
