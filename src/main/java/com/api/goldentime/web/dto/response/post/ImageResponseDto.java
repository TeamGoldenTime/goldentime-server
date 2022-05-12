package com.api.goldentime.web.dto.response.post;

import com.api.goldentime.domain.post.CatchImage;
import com.api.goldentime.domain.post.LostImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageResponseDto {

  private Long id;
  private String name;
  private String location;

  @Builder
  public ImageResponseDto(Long id, String name, String location) {
    this.id = id;
    this.name = name;
    this.location = location;
  }

  public static ImageResponseDto of(LostImage lostImage) {
    return ImageResponseDto.builder()
        .id(lostImage.getId())
        .name(lostImage.getName())
        .location(lostImage.getLocation())
        .build();
  }

  public static ImageResponseDto of(CatchImage catchImage) {
    return ImageResponseDto.builder()
        .id(catchImage.getId())
        .name(catchImage.getName())
        .location(catchImage.getLocation())
        .build();
  }
}

