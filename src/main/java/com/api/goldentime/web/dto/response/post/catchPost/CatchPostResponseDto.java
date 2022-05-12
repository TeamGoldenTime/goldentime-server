package com.api.goldentime.web.dto.response.post.catchPost;

import com.api.goldentime.domain.post.CatchImage;
import com.api.goldentime.domain.post.CatchPost;
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
    private String kind;
    private String gender;
    private String color;
    private String remark; //특이사항
    private String area;
    private LocalDateTime date;

    public static CatchPostResponseDto of (CatchPost catchPost) {
        return CatchPostResponseDto.builder()
            .id(catchPost.getId())
            .writer(catchPost.getWriter())
            .images(catchPost.getCatchImages())
            .latitude(catchPost.getRegion().getLatitude())
            .longitude(catchPost.getRegion().getLongitude())
            .kind(catchPost.getKind())
            .gender(catchPost.getGender())
            .color(catchPost.getColor())
            .remark(catchPost.getRemark())
            .area(catchPost.getArea())
            .date(catchPost.getDate())
            .build();
    }

    @Builder
    public CatchPostResponseDto(Long id ,User writer,
        List<CatchImage> images, Double latitude, Double longitude, String kind,
        String gender, String color, String remark, String area, LocalDateTime date) {
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
        this.remark = remark;
        this.area = area;
        this.date = date;
    }
}