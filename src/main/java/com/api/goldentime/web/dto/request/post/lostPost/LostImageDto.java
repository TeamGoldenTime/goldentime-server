package com.api.goldentime.web.dto.request.post.lostPost;

import com.api.goldentime.domain.post.LostImage;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LostImageDto {
  private String name;
  private String location;

  public LostImage toEntity() {
    return LostImage.builder()
        .name(getName())
        .location(getLocation())
        .build();
  }
}
