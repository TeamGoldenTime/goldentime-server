package com.api.goldentime.web.dto.response.post.catchPost;

import com.api.goldentime.domain.post.CatchImage;
import com.api.goldentime.domain.post.CatchPost;
import com.api.goldentime.domain.post.PostType;
import com.api.goldentime.domain.user.User;
import com.api.goldentime.web.dto.response.login.LoginResponseDto.UserResponseDto;
import com.api.goldentime.web.dto.response.post.ImageResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CatchPostResponseDto {

  private Long id;
  private UserResponseDto writer;
  private List<ImageResponseDto> images;
  private Double latitude;
  private Double longitude;
  private String addressName;
  private String region_1depth_name;
  private String region_2depth_name;
  private String region_3depth_name;
  private String kind;
  private String gender;
  private String color;
  private String remark; //특이사항
  private String area;
  private String type;
  private LocalDateTime date;

  public static CatchPostResponseDto of(CatchPost catchPost) {
    return CatchPostResponseDto.builder()
        .id(catchPost.getId())
        .writer(catchPost.getWriter())
        .images(catchPost.getCatchImages())
        .latitude(catchPost.getRegion().getLatitude())
        .longitude(catchPost.getRegion().getLongitude())
        .addressName(catchPost.getAddress().getAddressName())
        .region_1depth_name(catchPost.getAddress().getRegion_1depth_name())
        .region_2depth_name(catchPost.getAddress().getRegion_2depth_name())
        .region_3depth_name(catchPost.getAddress().getRegion_3depth_name())
        .kind(catchPost.getKind())
        .gender(catchPost.getGender())
        .color(catchPost.getColor())
        .remark(catchPost.getRemark())
        .area(catchPost.getArea())
        .type(catchPost.getType())
        .date(catchPost.getDate())
        .build();
  }

  @Builder
  public CatchPostResponseDto(Long id, User writer, List<CatchImage> images,
      Double latitude, Double longitude, String addressName, String region_1depth_name, String region_2depth_name,
      String region_3depth_name, String kind, String gender, String color, String remark,
      String area, PostType type, LocalDateTime date) {
    this.id = id;
    this.writer = new UserResponseDto(writer);
    this.images = images.stream()
        .map(ImageResponseDto::of)
        .collect(Collectors.toList());
    this.latitude = latitude;
    this.longitude = longitude;
    this.addressName = addressName;
    this.region_1depth_name = region_1depth_name;
    this.region_2depth_name = region_2depth_name;
    this.region_3depth_name = region_3depth_name;
    this.kind = kind;
    this.gender = gender;
    this.color = color;
    this.remark = remark;
    this.area = area;
    this.type = type.toString();
    this.date = date;
  }
}
