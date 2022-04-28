package com.api.goldentime.web.dto.request.post.lost;

import com.api.goldentime.domain.post.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageDto {
  private String name;
  private String location;

  public Image toEntity() {
    return Image.builder()
        .name(getName())
        .location(getLocation())
        .build();
  }
}
