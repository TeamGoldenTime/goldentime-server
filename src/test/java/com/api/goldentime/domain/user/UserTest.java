package com.api.goldentime.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  @DisplayName("유저 생성이 가능해야 한다.")
  void 생성() {
    User user = User.builder()
        .identifier("xxxxxxx")
        .email("asdf@naver.com")
        .phoneNumber("010-5245-5702")
        .name("test")
        .build();

    assertThat(user).isNotNull();
    assertThat(user.getEmail()).isEqualTo("asdf@naver.com");
  }

  @Test
  @DisplayName("identifier가 없을 경우 유저 생성에 실패해야 한다.")
  void 실패() {
    assertThrows(IllegalStateException.class, () -> {
      User.builder()
          .email("asdf@naver.com")
          .phoneNumber("010-5245-5702")
          .name("test")
          .build();
    });
  }
}
