package com.api.goldentime.web.dto.response.post.lostPost;

import com.api.goldentime.domain.post.LostPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LostPostListResponseDto {

    private List<LostPostResponseDto> lostPostList;

}
