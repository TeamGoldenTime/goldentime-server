package com.api.goldentime.web.dto.response.crawling;

import com.api.goldentime.domain.crawling.PetData;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDataResponseDto {

  private List<PetDataResponse> related;
  private List<PetDataResponse> unrelated;

  public static PetDataResponseDto of(List<PetData> related, List<PetData> unrelated) {
    return PetDataResponseDto.builder()
        .related(related.stream()
            .map(PetDataResponse::of)
            .collect(Collectors.toList()))
        .unrelated(unrelated.stream()
            .map(PetDataResponse::of)
            .collect(Collectors.toList()))
        .build();
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class PetDataResponse {
    private Long id;

    private String imgUrl;

    private String region_1depth_name;

    private String region_2depth_name;

    private String region_3depth_name;

    private String reportDate;

    private String kind;

    private String gender;

    private String lostPlace;

    private String remark;

    private String detailLink;


    public static PetDataResponse of(PetData petData) {
      return PetDataResponse.builder()
          .id(petData.getId())
          .imgUrl(petData.getImgUrl())
          .region_1depth_name(petData.getRegion_1depth_name())
          .region_2depth_name(petData.getRegion_2depth_name())
          .region_3depth_name(petData.getRegion_3depth_name())
          .reportDate(petData.getReportDate())
          .kind(petData.getKind())
          .gender(petData.getGender())
          .remark(petData.getRemark())
          .detailLink(petData.getDetailLink())
          .build();
    }
  }
}
