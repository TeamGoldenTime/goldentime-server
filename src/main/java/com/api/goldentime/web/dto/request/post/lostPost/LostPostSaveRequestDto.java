package com.api.goldentime.web.dto.request.post.lostPost;

import com.api.goldentime.domain.post.LostImage;
import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.domain.post.Region;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LostPostSaveRequestDto {

    @NotNull
    private Long userId;
    private List<LostImageDto> images;
    private Double latitude;
    private Double longitude;
    private String kind;
    private String color;
    private String name;
    private String remark; //특이사항
    private String area;
    private String gender;
    private LocalDateTime date;
    private int age;
    private int reward;


    public LostPost toEntity()
    {
        Region region = new Region(getLatitude(), getLongitude());
        List<LostImage> lostImages = getImages().stream().map(LostImageDto::toEntity).collect(Collectors.toList());

        LostPost lostpost = LostPost.builder()
            .region(region)
            .kind(getKind())
            .color(getColor())
            .name(getName())
            .remark(getRemark())
            .date(getDate())
            .age(getAge())
            .area(getArea())
            .gender(getGender())
            .reward(getReward())
            .build();

        lostpost.addImages(lostImages);
        return lostpost;
    }

}
