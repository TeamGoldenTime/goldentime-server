package com.api.goldentime.web.dto.response.post;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.web.dto.request.post.lost.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LostPostListResponseDto {

    private List<LostPost> lostPostList;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LostPostResponse {
        private Long userId;
        private List<ImageDto> images;
        private Double latitude;
        private Double longitude;
        private String kind;
        private String color;
        private String name;
        private String remark; //특이사항
        private String area;
        private LocalDateTime date;
        private int age;
        private int reward;

    }


}
