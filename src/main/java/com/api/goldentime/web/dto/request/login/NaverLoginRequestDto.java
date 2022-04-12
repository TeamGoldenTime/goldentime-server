package com.api.goldentime.web.dto.request.login;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NaverLoginRequestDto {

  @NotBlank
  private String accessToken;
  private String expireAt;
  private String refreshToken;
  private String tokenType;
}
