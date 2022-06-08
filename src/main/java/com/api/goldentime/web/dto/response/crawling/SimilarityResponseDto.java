package com.api.goldentime.web.dto.response.crawling;

import com.api.goldentime.domain.crawling.PetData;
import com.api.goldentime.domain.post.CatchPost;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimilarityResponseDto {

  private List<SimilarityResponse> related;
  private List<SimilarityResponse> unrelated;

  public static SimilarityResponseDto of(List<SimilarityResponse> related,
      List<SimilarityResponse> unrelated) {
    return SimilarityResponseDto.builder()
        .related(related)
        .unrelated(unrelated)
        .build();
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class SimilarityResponse {

    private Long id;

    private String type;

    private String writer;

    private String imgUrl;

    private String region_1depth_name;

    private String region_2depth_name;

    private String region_3depth_name;

    private String date;

    private String kind;

    private String gender;

    private String area;

    private String remark;

    private String detailLink;


    public static SimilarityResponse of(PetData petData) {
      return SimilarityResponse.builder()
          .id(petData.getId())
          .type("shelter")
          .imgUrl(petData.getImgUrl())
          .writer("동물보호 관리 시스템")
          .region_1depth_name(petData.getRegion_1depth_name())
          .region_2depth_name(petData.getRegion_2depth_name())
          .region_3depth_name(petData.getRegion_3depth_name())
          .date(petData.getReportDate())
          .kind(petData.getKind())
          .gender(petData.getGender())
          .area(petData.getLostPlace())
          .remark(petData.getRemark())
          .detailLink(petData.getDetailLink())
          .build();
    }

    public static SimilarityResponse of(CatchPost catchPost) {
      return SimilarityResponse.builder()
          .id(catchPost.getId())
          .type("catch")
          .imgUrl(catchPost.getCatchImages().get(0).getLocation())
          .writer(catchPost.getWriter().getName())
          .region_1depth_name(catchPost.getAddress().getRegion_1depth_name())
          .region_2depth_name(catchPost.getAddress().getRegion_2depth_name())
          .region_3depth_name(catchPost.getAddress().getRegion_3depth_name())
          .date(catchPost.getDate().toString())
          .kind(catchPost.getKind())
          .area(catchPost.getArea())
          .gender(catchPost.getGender())
          .remark(catchPost.getRemark())
          .build();
    }

  }
}
