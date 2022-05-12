package com.api.goldentime.web.dto.response.post.lostPost;

import com.api.goldentime.domain.post.LostImage;
import com.api.goldentime.domain.post.LostPost;
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
public class LostPostResponseDto {

    private Long id;
    private UserResponseDto writer;
    private List<ImageResponseDto> images;
    private Double latitude;
    private Double longitude;
    private String kind;
    private String gender;
    private String color;
    private String name;
    private String remark; //특이사항
    private String area;
    private LocalDateTime date;
    private int age;
    private int reward;

    public static LostPostResponseDto of (LostPost lostPost) {
        return LostPostResponseDto.builder()
            .id(lostPost.getId())
            .writer(lostPost.getWriter())
            .images(lostPost.getLostImages())
            .latitude(lostPost.getRegion().getLatitude())
            .longitude(lostPost.getRegion().getLongitude())
            .kind(lostPost.getKind())
            .gender(lostPost.getGender())
            .color(lostPost.getColor())
            .name(lostPost.getName())
            .remark(lostPost.getRemark())
            .area(lostPost.getArea())
            .date(lostPost.getDate())
            .age(lostPost.getAge())
//            .remark(lostPost.getReward())
            .build();
    }

    @Builder
    public LostPostResponseDto(
        Long id, User writer,
        List<LostImage> images, Double latitude, Double longitude, String kind,
        String gender, String color, String name, String remark, String area,
        LocalDateTime date, int age, int reward) {
        this.id = id;
        this.writer = new UserResponseDto(writer);
        this.images = images.stream()
            .map(ImageResponseDto::of)
            .collect(Collectors.toList());
        this.latitude = latitude;
        this.longitude = longitude;
        this.kind = kind;
        this.gender = gender;
        this.color = color;
        this.name = name;
        this.remark = remark;
        this.area = area;
        this.date = date;
        this.age = age;
        this.reward = reward;
    }
}