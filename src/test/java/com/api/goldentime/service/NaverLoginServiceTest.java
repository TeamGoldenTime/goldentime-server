package com.api.goldentime.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.api.goldentime.domain.user.User;
import com.api.goldentime.repository.UserRepository;
import com.api.goldentime.service.login.NaverLoginService;
import com.api.goldentime.web.dto.oauth.NaverProfileDto;
import com.api.goldentime.web.dto.oauth.NaverProfileDto.NaverProfileResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NaverLoginServiceTest {
  @InjectMocks
  private NaverLoginService naverLoginService;

  @Mock
  private UserRepository userRepository;

  @Test
  void 로그인() {
    NaverProfileDto naverProfileDto = createNaverProfileDto();
    User user = naverProfileDto.toUserEntity();

    given(userRepository.save(any())).willReturn(user);

    User loginUser = naverLoginService.login(naverProfileDto);

    assertThat(loginUser.getIdentifier()).isEqualTo(naverProfileDto.getResponse().getId());
    assertThat(loginUser.getEmail()).isEqualTo(naverProfileDto.getResponse().getEmail());
  }

  private NaverProfileDto createNaverProfileDto() {
    String id = "fwefads-fwfw";
    String name = "test";
    String email = "test@naver.com";
    String mobile = "010-1234-5678";
    String gender = "M";

    return new NaverProfileDto("200", "success",
        new NaverProfileResponse(
            id,
            name,
            email,
            mobile,
            gender
        ));
  }
}
