package com.api.goldentime.web.controller;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.service.post.LostPostService;
import com.api.goldentime.web.dto.request.post.ImageRequestDto;
import com.api.goldentime.web.dto.request.post.lostPost.LostPostSaveRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.post.ImageResponseDto;
import com.api.goldentime.web.dto.response.post.lostPost.LostPostListResponseDto;
import com.api.goldentime.web.dto.response.post.lostPost.LostPostResponseDto;
import com.api.goldentime.web.dto.response.post.lostPost.LostPostSaveResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @ApiOperation(value = "분실 신고 게시물 목록 반환")
    @GetMapping("/pet/lost/postList")
    public ResponseEntity<ResponseDto<LostPostListResponseDto>> postList()
    {
        List<LostPost> lostPostList = lostPostService.getLostPostList();
        LostPostListResponseDto lostPostListResponseDto = new LostPostListResponseDto(lostPostList);

        ResponseDto<LostPostListResponseDto> response = ResponseDto.<LostPostListResponseDto>builder()
                .status(ResponseDto.ResponseStatus.SUCCESS)
                .message("분실 신고 게시물 목록 조회 성공")
                .data(lostPostListResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "분실 신고 게시물 상세 정보 조회")
    @GetMapping("/pet/lost/{id}")
    public ResponseEntity<ResponseDto<LostPostResponseDto>> getDetailInfo(@PathVariable Long id)
    {
       LostPost lostPost = lostPostService.findById(id);
       LostPostResponseDto lostPostResponseDto = LostPostResponseDto.builder()
               .writer(lostPost.getWriter())
               // .images(lostPost.getImages())
               .kind(lostPost.getKind())
               .color(lostPost.getColor())
               .name(lostPost.getName())
               .remark(lostPost.getRemark())
               .area(lostPost.getArea())
               .date(lostPost.getDate())
               .age(lostPost.getAge())
               .reward(lostPost.getReward())
               .build();

       ResponseDto<LostPostResponseDto> response = ResponseDto.<LostPostResponseDto>builder()
               .status(ResponseDto.ResponseStatus.SUCCESS)
               .message("분실 신고 게시물 목록 조회 성공")
               .data(lostPostResponseDto)
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

        ResponseDto<ImageResponseDto> response = ResponseDto.<ImageResponseDto>builder()
            .status(ResponseDto.ResponseStatus.SUCCESS)
            .message("이미지 분석 결과 반환 성공")
            .data(imageResponseDto)
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
