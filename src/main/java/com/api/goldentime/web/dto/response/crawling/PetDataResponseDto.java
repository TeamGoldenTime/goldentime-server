package com.api.goldentime.web.dto.response.crawling;

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
}
