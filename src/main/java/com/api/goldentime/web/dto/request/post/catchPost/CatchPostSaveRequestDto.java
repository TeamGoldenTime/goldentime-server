package com.api.goldentime.web.dto.request.post.catchPost;

import com.api.goldentime.domain.post.CatchImage;
import com.api.goldentime.domain.post.CatchPost;
import com.api.goldentime.domain.post.Region;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.api.goldentime.web.dto.request.post.lostPost.LostImageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatchPostSaveRequestDto {

    @NotNull
    private Long userId;
    private List<CatchImageDto> images;
    private Double latitude;
    private Double longitude;
    private String kind;
    private String color;
    private String remark; //특이사항
    private String area;
    private LocalDateTime date;

    public CatchPost toEntity()
    {
        Region region = new Region(getLatitude(), getLongitude());
        List<CatchImage> catchImages = getImages().stream().map(CatchImageDto::toEntity).collect(Collectors.toList());

        CatchPost catchpost = CatchPost.builder()
                .region(region)
                .kind(getKind())
                .color(getColor())
                .remark(getRemark())
                .date(getDate())
                .area(getArea())
                .build();

        catchpost.addImages(catchImages);
        return catchpost;
    }



}
