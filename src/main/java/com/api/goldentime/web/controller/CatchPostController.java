package com.api.goldentime.web.controller;

import com.api.goldentime.domain.post.Address;
import com.api.goldentime.domain.post.CatchPost;
import com.api.goldentime.service.post.CatchPostService;
import com.api.goldentime.web.dto.request.post.catchPost.CatchPostSaveRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.post.catchPost.CatchPostResponseDto;
import com.api.goldentime.web.dto.response.post.catchPost.CatchPostSaveResponseDto;
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
@Api("CatchPost Controller")
public class CatchPostController {

    private final CatchPostService catchPostService;

    @ApiOperation(value = "목격 신고 등록")
    @PostMapping("/pet/post/catch")
    public ResponseEntity<ResponseDto<CatchPostSaveResponseDto>> save(@RequestBody @Valid CatchPostSaveRequestDto catchPostSaveRequestDto)
    {
        //Address address = KakaoApiController.getAddress(lat , lng);
        //CatchPost post = catchPostService.save(catchPostSaveRequestDto, address);
        CatchPost post = catchPostService.save(catchPostSaveRequestDto);

        CatchPostSaveResponseDto catchPostSaveResponseDto = CatchPostSaveResponseDto.builder()
                .id(post.getId())
                .kind(post.getKind())
                .color(post.getColor())
                .build();

        ResponseDto<CatchPostSaveResponseDto> response = ResponseDto.<CatchPostSaveResponseDto>builder()
                .status(ResponseDto.ResponseStatus.SUCCESS)
                .message("목격 신고 게시물 등록 성공")
                .data(catchPostSaveResponseDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "목격 신고 게시물 목록 반환")
    @GetMapping("/pet/post/catch")
    public ResponseEntity<ResponseDto<List<CatchPostResponseDto>>> postList()
    {
        //목록 조회
        List<CatchPost> catchPostList = catchPostService.getCatchPostList();

        //dto로 변경
        List<CatchPostResponseDto> catchPostListDto = catchPostList.stream()
            .map(CatchPostResponseDto::of)
            .collect(Collectors.toList());

        //ResponseDto 생성
        ResponseDto<List<CatchPostResponseDto>> response = ResponseDto.<List<CatchPostResponseDto>>builder()
            .status(ResponseDto.ResponseStatus.SUCCESS)
            .message("목격 신고 게시물 목록 조회 성공")
            .data(catchPostListDto)
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "목격 신고 게시물 상세 정보 조회")
    @GetMapping("/pet/post/catch/{id}")
    public ResponseEntity<ResponseDto<CatchPostResponseDto>> getDetailInfo(@PathVariable Long id)
    {
        CatchPost catchPost = catchPostService.findById(id);
        CatchPostResponseDto catchPostResponseDto = CatchPostResponseDto.of(catchPost);

        ResponseDto<CatchPostResponseDto> response = ResponseDto.<CatchPostResponseDto>builder()
            .status(ResponseDto.ResponseStatus.SUCCESS)
            .message("목격 신고 게시물 조회 성공")
            .data(catchPostResponseDto)
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}
