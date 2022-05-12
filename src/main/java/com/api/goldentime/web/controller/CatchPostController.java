package com.api.goldentime.web.controller;

import com.api.goldentime.domain.post.CatchPost;
import com.api.goldentime.service.post.CatchPostService;
import com.api.goldentime.web.dto.request.post.ImageRequestDto;
import com.api.goldentime.web.dto.request.post.catchPost.CatchPostSaveRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.post.ImageResponseDto;
import com.api.goldentime.web.dto.response.post.catchPost.CatchPostListResponseDto;
import com.api.goldentime.web.dto.response.post.catchPost.CatchPostResponseDto;
import com.api.goldentime.web.dto.response.post.catchPost.CatchPostSaveResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api("CatchPost Controller")
public class CatchPostController {

    private final CatchPostService catchPostService;

    @ApiOperation(value = "목격 신고 등록")
    @PostMapping("/pet/catch")
    public ResponseEntity<ResponseDto<CatchPostSaveResponseDto>> save(@RequestBody @Valid CatchPostSaveRequestDto catchPostSaveRequestDto)
    {
        CatchPost post = catchPostService.save(catchPostSaveRequestDto);

        CatchPostSaveResponseDto catchPostSaveResponseDto = CatchPostSaveResponseDto.builder()
                .id(post.getId())
                .kind(post.getKind())
                .color(post.getColor())
                .build();

        ResponseDto<CatchPostSaveResponseDto> response = ResponseDto.<CatchPostSaveResponseDto>builder()
                .status(ResponseDto.ResponseStatus.SUCCESS)
                .message("분실 신고 게시물 등록 성공")
                .data(catchPostSaveResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "목격 신고 게시물 목록 반환")
    @GetMapping("/pet/catch/postList")
    public ResponseEntity<ResponseDto<CatchPostListResponseDto>> postList()
    {
        List<CatchPost> catchPostList = catchPostService.getCatchPostList();
        CatchPostListResponseDto catchPostListResponseDto = new CatchPostListResponseDto(catchPostList);

        ResponseDto<CatchPostListResponseDto> response = ResponseDto.<CatchPostListResponseDto>builder()
                .status(ResponseDto.ResponseStatus.SUCCESS)
                .message("목격 신고 게시물 목록 조회 성공")
                .data(catchPostListResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "목격 신고 게시물 상세 정보 조회")
    @GetMapping("/pet/catch/{id}")
    public ResponseEntity<ResponseDto<CatchPostResponseDto>> getDetailInfo(@PathVariable Long id)
    {
        CatchPost catchPost = catchPostService.findById(id);
        CatchPostResponseDto catchPostResponseDto = CatchPostResponseDto.builder()
                .writer(catchPost.getWriter())
                // .images(catchPost.getImages())
                .kind(catchPost.getKind())
                .color(catchPost.getColor())
                .remark(catchPost.getRemark())
                .area(catchPost.getArea())
                .date(catchPost.getDate())
                .build();

        ResponseDto<CatchPostResponseDto> response = ResponseDto.<CatchPostResponseDto>builder()
                .status(ResponseDto.ResponseStatus.SUCCESS)
                .message("목격 신고 게시물 목록 조회 성공")
                .data(catchPostResponseDto)
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
