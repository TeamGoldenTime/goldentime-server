package com.api.goldentime.web.controller;

import com.api.goldentime.web.dto.request.post.ImageRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.post.ImageResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api("Pet Controller")
public class PetController {

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

        ResponseDto<ImageResponseDto> response = ResponseDto.<ImageResponseDto>builder()
                .status(ResponseDto.ResponseStatus.SUCCESS)
                .message("이미지 분석 결과 반환 성공")
                .data(imageResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
