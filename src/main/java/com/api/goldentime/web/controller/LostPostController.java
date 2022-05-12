package com.api.goldentime.web.controller;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.service.post.LostPostService;
import com.api.goldentime.web.dto.request.post.lostPost.LostPostSaveRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.post.lostPost.LostPostResponseDto;
import com.api.goldentime.web.dto.response.post.lostPost.LostPostSaveResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api("LostPost Controller")
public class LostPostController {

  private final LostPostService lostPostService;

  @ApiOperation(value = "분실 신고 등록")
  @PostMapping("/pet/post/lost")
  public ResponseEntity<ResponseDto<LostPostSaveResponseDto>> save(
      @RequestBody @Valid LostPostSaveRequestDto lostPostSaveRequestDto) {
    LostPost post = lostPostService.save(lostPostSaveRequestDto);

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


}
