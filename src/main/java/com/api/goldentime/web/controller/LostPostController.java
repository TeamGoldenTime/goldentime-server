package com.api.goldentime.web.controller;

import com.api.goldentime.domain.crawling.PetData;
import com.api.goldentime.domain.post.Address;
import com.api.goldentime.domain.post.CatchPost;
import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.repository.CatchPostRepository;
import com.api.goldentime.repository.PetDataRepository;
import com.api.goldentime.service.post.LostPostService;
import com.api.goldentime.web.dto.request.post.lostPost.LostPostSaveRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.ResponseDto.ResponseStatus;
import com.api.goldentime.web.dto.response.crawling.SimilarityResponseDto;
import com.api.goldentime.web.dto.response.crawling.SimilarityResponseDto.SimilarityResponse;
import com.api.goldentime.web.dto.response.post.SimilarImageResponseDto;
import com.api.goldentime.web.dto.response.post.lostPost.LostPostResponseDto;
import com.api.goldentime.web.dto.response.post.lostPost.LostPostSaveResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Api("LostPost Controller")
public class LostPostController {

  private final LostPostService lostPostService;
  private final PetDataRepository petDataRepository;
  private final CatchPostRepository catchPostRepository;


  @ApiOperation(value = "분실 신고 등록")
  @PostMapping("/pet/post/lost")
  public ResponseEntity<ResponseDto<LostPostSaveResponseDto>> save(
      @RequestBody @Valid LostPostSaveRequestDto lostPostSaveRequestDto) {

    Double latitude = lostPostSaveRequestDto.getLatitude();
    Double longitude = lostPostSaveRequestDto.getLongitude();

    Address address = KakaoApiController.kakaoApi(latitude, longitude);


    LostPost post = lostPostService.save(lostPostSaveRequestDto, address);

    LostPostSaveResponseDto lostPostSaveResponseDto = LostPostSaveResponseDto.builder()
        .id(post.getId())
        .build();

    ResponseDto<LostPostSaveResponseDto> response = ResponseDto.<LostPostSaveResponseDto>builder()
        .status(ResponseDto.ResponseStatus.SUCCESS)
        .message("분실 신고 게시물 등록 성공")
        .data(lostPostSaveResponseDto)
        .build();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @ApiOperation(value = "분실 신고 게시물 목록 반환")
  @GetMapping("/pet/post/lost")
  public ResponseEntity<ResponseDto<List<LostPostResponseDto>>> postList() {
    //목록 조회
    List<LostPost> lostPostList = lostPostService.getLostPostList();

    //dto로 변경
    List<LostPostResponseDto> lostPostListDto = lostPostList.stream()
        .map(LostPostResponseDto::of)
        .collect(Collectors.toList());

    //ResponseDto 생성
    ResponseDto<List<LostPostResponseDto>> response = ResponseDto.<List<LostPostResponseDto>>builder()
        .status(ResponseDto.ResponseStatus.SUCCESS)
        .message("분실 신고 게시물 목록 조회 성공")
        .data(lostPostListDto)
        .build();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @ApiOperation(value = "분실 신고 게시물 상세 정보 조회")
  @GetMapping("/pet/post/lost/{id}")
  public ResponseEntity<ResponseDto<LostPostResponseDto>> getDetailInfo(@PathVariable Long id) {
    LostPost lostPost = lostPostService.findById(id);
    LostPostResponseDto lostPostResponseDto = LostPostResponseDto.of(lostPost);

    ResponseDto<LostPostResponseDto> response = ResponseDto.<LostPostResponseDto>builder()
        .status(ResponseDto.ResponseStatus.SUCCESS)
        .message("분실 신고 게시물 목록 조회 성공")
        .data(lostPostResponseDto)
        .build();

    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  @ApiOperation(value = "유사 신고 분석")
  @GetMapping("/pet/post/lost/similarity/{id}")
  public ResponseEntity<?> getSimilarityImage(@PathVariable Long id) {
    LostPost lostPost = lostPostService.findById(id);

    RestTemplate restTemplate = new RestTemplate();
    String apiURL = "http://127.0.0.1:5000/image_similarity_inference";

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Content-Type", "application/json");

    Map<String, String> request = new HashMap<>();
    request.put("path", lostPost.getLostImages().get(0).getLocation());
    HttpEntity<?> httpEntity = new HttpEntity<>(request, httpHeaders);

    List<SimilarImageResponseDto> similarImageResponseDto = restTemplate.exchange(apiURL,
        HttpMethod.POST, httpEntity,
        new ParameterizedTypeReference<List<SimilarImageResponseDto>>() {})
        .getBody();

    if (similarImageResponseDto == null) {
      throw new IllegalArgumentException("분석 결과를 받아올 수 없습니다.");
    }

    List<PetData> petDataList = similarImageResponseDto.stream()
        .map(s -> petDataRepository.findByImgUrl(s.getId()).orElse(null))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    List<CatchPost> catchPostList = similarImageResponseDto.stream()
        .map(s -> catchPostRepository.findByImgUrl(s.getId()).orElse(null))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    List<SimilarityResponse> related = new ArrayList<>();
    List<SimilarityResponse> unrelated = new ArrayList<>();

    catchPostList.forEach(
        c -> {
          if (c.getAddress().getRegion_1depth_name().equals(lostPost.getAddress().getRegion_1depth_name())) {
            related.add(SimilarityResponse.of(c));
          } else {
            unrelated.add(SimilarityResponse.of(c));
          }
        }
    );

    petDataList.forEach(
        p -> {
          if (p.getRegion_1depth_name().equals(lostPost.getAddress().getRegion_1depth_name())) {
            related.add(SimilarityResponse.of(p));
        } else {
            unrelated.add(SimilarityResponse.of(p));
          }
        }
    );

    SimilarityResponseDto petDataResponseDto = SimilarityResponseDto.of(related, unrelated);

    ResponseDto<SimilarityResponseDto> response = ResponseDto.<SimilarityResponseDto>builder()
        .status(ResponseDto.ResponseStatus.SUCCESS)
        .data(petDataResponseDto)
        .build();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @ApiOperation(value = "자신의 분실신고 정보 가져오기")
  @GetMapping("/pet/post/lost/{userId}/me")
  public ResponseEntity<ResponseDto<List<LostPostResponseDto>>> getLostPostByUser(@PathVariable Long userId) {

    List<LostPost> lostPosts = lostPostService.getLostPostByUser(userId);

    List<LostPostResponseDto> lostPostResponseDtoList = lostPosts.stream()
        .map(LostPostResponseDto::of)
        .collect(Collectors.toList());

    ResponseDto<List<LostPostResponseDto>> response = ResponseDto.<List<LostPostResponseDto>>builder()
        .status(ResponseStatus.SUCCESS)
        .data(lostPostResponseDtoList)
        .build();

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
