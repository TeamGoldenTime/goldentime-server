package com.api.goldentime.web.dto.response.post;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.web.dto.request.post.lost.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LostPostListResponseDto {

    private List<LostPost> lostPostList;

}
