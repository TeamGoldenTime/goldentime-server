package com.api.goldentime.web.dto.request.post.catchPost;

import com.api.goldentime.domain.post.CatchImage;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CatchImageRequestDto {

    private String name;
    private String location;

    public CatchImage toEntity() {
        return CatchImage.builder()
                .name(getName())
                .location(getLocation())
                .build();
    }


}
