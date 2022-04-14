package com.api.goldentime.web.dto.response.login;

import com.api.goldentime.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {

  private final String token;
  private final UserResponseDto user;

  @Builder
  public LoginResponseDto(String token, User user) {
    this.token = token;
    this.user = new UserResponseDto(user);
  }

  @Getter
  public static class UserResponseDto {
    private final Long id;
    private final String email;
    private final String name;
    private final String phoneNumber;
    private final String gender;

    public UserResponseDto(User user) {
      this.id = user.getId();
      this.email = user.getEmail();
      this.name = user.getName();
      this.phoneNumber = user.getPhoneNumber();
      this.gender = user.getGender();
    }
  }
}
