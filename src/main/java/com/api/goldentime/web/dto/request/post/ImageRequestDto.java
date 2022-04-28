package com.api.goldentime.web.dto.request.post;

import com.api.goldentime.web.dto.request.post.lost.ImageDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageRequestDto {

  private List<ImageDto> images;
}
