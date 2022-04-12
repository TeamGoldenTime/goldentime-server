package com.api.goldentime.web;

import com.api.goldentime.domain.user.User;
import com.api.goldentime.service.NaverLoginService;
import com.api.goldentime.web.dto.oauth.NaverProfileDto;
import com.api.goldentime.web.dto.response.LoginResponseDto;
import com.api.goldentime.web.dto.request.login.NaverLoginRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.ResponseDto.ResponseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api("Auth Controller")
public class AuthController {
  private final NaverLoginService naverLoginService;

  @ApiOperation(value="네이버 로그인")
  @PostMapping("/auth/naver")
  public ResponseEntity<ResponseDto<LoginResponseDto>> naver(@RequestBody @Valid NaverLoginRequestDto naverLoginRequestDto) {
    NaverProfileDto naverProfileDto = naverLoginService.getProfile(naverLoginRequestDto);
    User user = naverLoginService.login(naverProfileDto);

    LoginResponseDto loginResponseDto = LoginResponseDto.builder()
        .user(user)
        .token("test-token") //서비스용 토큰 발급
        .build();

    ResponseDto<LoginResponseDto> response = ResponseDto.<LoginResponseDto>builder()
        .status(ResponseStatus.SUCCESS)
        .message("네이버 로그인 성공")
        .data(loginResponseDto)
        .build();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
