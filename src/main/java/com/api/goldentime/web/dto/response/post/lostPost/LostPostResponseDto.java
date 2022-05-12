package com.api.goldentime.web.dto.response.post.lostPost;

import com.api.goldentime.domain.user.User;
import com.api.goldentime.web.dto.request.post.lostPost.LostImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class LostPostResponseDto {

    private User writer;
    private List<LostImageDto> images;
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
