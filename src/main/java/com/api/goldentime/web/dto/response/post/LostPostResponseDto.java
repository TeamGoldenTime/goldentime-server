package com.api.goldentime.web.dto.response.post;

import com.api.goldentime.domain.user.User;
import com.api.goldentime.web.dto.request.post.lost.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class LostPostResponseDto {

    private User writer;
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


    @Builder
    public LostPostResponseDto(User writer, List<ImageDto> images, Double latitude, Double longitude,
                               String kind, String color, String name, String remark, String area, LocalDateTime date, int age, int reward) {
        this.writer = writer;
        this.images = images;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kind = kind;
        this.color = color;
        this.name = name;
        this.remark = remark;
        this.area = area;
        this.date = date;
        this.age = age;
        this.reward = reward;
    }
}
