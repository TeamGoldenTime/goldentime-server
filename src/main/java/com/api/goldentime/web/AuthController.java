package com.api.goldentime.web;

import com.api.goldentime.domain.user.NaverProfile;
import com.api.goldentime.domain.user.User;
import com.api.goldentime.service.NaverLoginService;
import com.api.goldentime.web.dto.LoginResponseDto;
import com.api.goldentime.web.dto.NaverLoginRequestDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
  private final NaverLoginService naverLoginService;

  @PostMapping("/auth/naver")
  public ResponseEntity<LoginResponseDto> naver(@RequestBody @Valid NaverLoginRequestDto naverLoginRequestDto) {
    NaverProfile profile = naverLoginService.getProfile(naverLoginRequestDto);
    User user = naverLoginService.login(profile);
    LoginResponseDto response = LoginResponseDto.builder()
        .user(user)
        .token("test-token")
        .build();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
