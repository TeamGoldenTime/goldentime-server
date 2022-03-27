package com.api.goldentime.service;

import com.api.goldentime.domain.user.NaverProfile;
import com.api.goldentime.domain.user.User;
import com.api.goldentime.repository.UserRepository;
import com.api.goldentime.web.dto.NaverLoginRequestDto;
import com.api.goldentime.web.dto.NaverProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NaverLoginService implements LoginService<NaverLoginRequestDto, NaverProfile> {

  private final UserRepository userRepository;

  public NaverProfile getProfile(NaverLoginRequestDto requestDto) {
    RestTemplate restTemplate = new RestTemplate();
    String token = requestDto.getAccessToken();
    String header = "Bearer " + token;
    String apiURL = "https://openapi.naver.com/v1/nid/me";

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", header);

    HttpEntity<?> request = new HttpEntity<>(httpHeaders);

    NaverProfileDto naverProfileDto = restTemplate.exchange(apiURL, HttpMethod.GET, request,
        NaverProfileDto.class).getBody();
    if (naverProfileDto == null || naverProfileDto.getResultcode().equals("024")) {
      throw new IllegalStateException();
    }

    return naverProfileDto.toEntity();
  }

  @Transactional
  public User login(NaverProfile profile) {
    User user = User.create(profile);

    //해당 user정보로 DB에서 검색

    //존재하면 해당 유저 return

    //없으면 DB에 저장하고 return (DB에 저장할때, email 중복여부 체크 필요)

    return user;
  }
}
