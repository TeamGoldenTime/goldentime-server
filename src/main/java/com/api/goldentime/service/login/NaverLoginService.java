package com.api.goldentime.service.login;

import com.api.goldentime.domain.user.User;
import com.api.goldentime.exception.NaverLoginFailException;
import com.api.goldentime.repository.UserRepository;
import com.api.goldentime.service.login.LoginService;
import com.api.goldentime.web.dto.oauth.NaverProfileDto;
import com.api.goldentime.web.dto.request.login.NaverLoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NaverLoginService implements LoginService<NaverLoginRequestDto, NaverProfileDto> {

  private final UserRepository userRepository;

  public NaverProfileDto getProfile(NaverLoginRequestDto requestDto) {
    RestTemplate restTemplate = new RestTemplate();
    String token = requestDto.getAccessToken();
    String header = "Bearer " + token;
    String apiURL = "https://openapi.naver.com/v1/nid/me";

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", header);

    HttpEntity<?> request = new HttpEntity<>(httpHeaders);

    try {
      NaverProfileDto naverProfileDto = restTemplate.exchange(apiURL, HttpMethod.GET, request,
          NaverProfileDto.class).getBody();

      return naverProfileDto;
    } catch (HttpClientErrorException ex) {
      throw new NaverLoginFailException();
    }

  }

  @Transactional
  public User login(NaverProfileDto naverProfileDto) {
    User user = naverProfileDto.toUserEntity();

    //해당 user정보로 DB에서 검색
    User findUser = userRepository
        .findByIdentifier(user.getIdentifier())
        .orElse(null);

    //존재하면 해당 유저 return
    if (findUser != null) {
      return findUser;
    }

    //없으면 DB에 저장하고 return (DB에 저장할때, email 중복여부 체크 필요)
    return userRepository.save(user);
  }
}
