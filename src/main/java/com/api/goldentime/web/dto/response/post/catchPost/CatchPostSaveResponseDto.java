package com.api.goldentime.web.dto.response.post.catchPost;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CatchPostSaveResponseDto {

    private final Long id;
    private final String kind;
    private final String color;

}
