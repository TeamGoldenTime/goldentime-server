package com.api.goldentime.web.controller;

import com.api.goldentime.web.dto.request.post.ImageRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.post.BreedColorResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<ResponseDto<BreedColorResponseDto>> analyze(@RequestBody @Valid ImageRequestDto imageRequestDto)
    {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = "http://127.0.0.1:5000/inference";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        Map<String, String> request = new HashMap<>();
        request.put("path", imageRequestDto.getImages().get(0).getLocation());
        HttpEntity<?> httpEntity = new HttpEntity<>(request, httpHeaders);

        BreedColorResponseDto result = restTemplate.exchange(apiURL, HttpMethod.POST, httpEntity,
                BreedColorResponseDto.class).getBody();

        if (result == null) {
            throw new IllegalArgumentException("분석 결과를 받아올 수 없습니다.");
        }

        BreedColorResponseDto breedColorResponseDto = BreedColorResponseDto.builder()
                .breed(result.getBreed())
                .build();

        ResponseDto<BreedColorResponseDto> response = ResponseDto.<BreedColorResponseDto>builder()
                .status(ResponseDto.ResponseStatus.SUCCESS)
                .message("이미지 분석 결과 반환 성공")
                .data(breedColorResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
