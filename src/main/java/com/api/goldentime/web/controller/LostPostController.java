package com.api.goldentime.web.controller;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.service.post.LostPostService;
import com.api.goldentime.web.dto.request.post.lost.LostPostSaveRequestDto;
import com.api.goldentime.web.dto.response.ResponseDto;
import com.api.goldentime.web.dto.response.post.LostPostSaveResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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



}
