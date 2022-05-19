package com.api.goldentime.web.dto.response.crawling;

import com.api.goldentime.domain.crawling.PetData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDataResponseDto {

  private Long id;

  private String imgUrl;

  private String postNum;

  private String reportDate;

  private String kind;

  private String gender;

  private String lostPlace;

  private String remark;

  private String detailLink;

  public static PetDataResponseDto of(PetData petData) {
    return PetDataResponseDto.builder()
        .id(petData.getId())
        .imgUrl(petData.getImgUrl())
        .postNum(petData.getPostNum())
        .reportDate(petData.getReportDate())
        .kind(petData.getKind())
        .gender(petData.getGender())
        .lostPlace(petData.getLostPlace())
        .remark(petData.getRemark())
        .detailLink(petData.getDetailLink())
        .build();
  }
}
