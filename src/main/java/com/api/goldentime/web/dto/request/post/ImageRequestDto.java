package com.api.goldentime.web.dto.request.post;

import java.util.List;

import com.api.goldentime.web.dto.request.post.lostPost.LostImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageRequestDto {

  private List<LostImageDto> images;
}
