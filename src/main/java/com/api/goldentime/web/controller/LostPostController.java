package com.api.goldentime.web.controller;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.exception.NaverLoginFailException;
import com.api.goldentime.service.post.LostPostService;
import com.api.goldentime.web.dto.oauth.NaverProfileDto;
import com.api.goldentime.web.dto.request.post.ImageRequestDto;
import com.api.goldentime.web.dto.request.post.lost.LostPostSaveRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.post.ImageResponseDto;
import com.api.goldentime.web.dto.response.post.LostPostSaveResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Api("LostPost Controller")
public class LostPostController {

    private final LostPostService lostPostService;

    @ApiOperation(value="분실 신고 등록")
    @PostMapping("/pet/lost")
    public ResponseEntity<ResponseDto<LostPostSaveResponseDto>> save(@RequestBody @Valid LostPostSaveRequestDto lostPostSaveRequestDto)
    {
        LostPost post = lostPostService.save(lostPostSaveRequestDto);

        LostPostSaveResponseDto lostPostSaveResponseDto = LostPostSaveResponseDto.builder()
                .id(post.getId())
                .kind(post.getKind())
                .color(post.getColor())
                .build();

        ResponseDto<LostPostSaveResponseDto> response = ResponseDto.<LostPostSaveResponseDto>builder()
                .status(ResponseDto.ResponseStatus.SUCCESS)
                .message("분실 신고 게시물 등록 성공")
                .data(lostPostSaveResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "이미지 분석 결과 반환")
    @PostMapping("/pet/analyze")
    public ResponseEntity<ResponseDto<ImageResponseDto>> analyze(@RequestBody @Valid ImageRequestDto imageRequestDto)
    {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = "http://127.0.0.1:5000/breed";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        HttpEntity<?> request = new HttpEntity<>(imageRequestDto, httpHeaders);

        ImageResponseDto result = restTemplate.exchange(apiURL, HttpMethod.POST, request,
                ImageResponseDto.class).getBody();

        if (result == null) {
            throw new IllegalArgumentException("분석 결과를 받아올 수 없습니다.");
        }

        ImageResponseDto imageResponseDto = ImageResponseDto.builder()
            .breed(result.getBreed())
            .build();

        //리턴
        ResponseDto<ImageResponseDto> response = ResponseDto.<ImageResponseDto>builder()
            .status(ResponseDto.ResponseStatus.SUCCESS)
            .message("이미지 분석 결과 반환 성공")
            .data(imageResponseDto)
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
