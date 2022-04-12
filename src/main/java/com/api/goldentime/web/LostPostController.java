package com.api.goldentime.web;

import com.api.goldentime.service.PostService;
import com.api.goldentime.web.dto.request.post.LostPostSaveRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api("LostPost Controller")
public class LostPostController {

    private final PostService postService;

    @ApiOperation(value="분실신고")
    @PostMapping("/pet/lost")
    public void save(@RequestBody @Valid LostPostSaveRequestDto lostPostSaveRequestDto)
    {
        postService.save(lostPostSaveRequestDto);
        //return new ResponseEntity<>(, HttpStatus.OK);
    }

}
