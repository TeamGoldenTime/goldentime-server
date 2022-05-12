package com.api.goldentime.web.dto.response.post.lostPost;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LostPostSaveResponseDto {

    private final Long id;
    private final String kind;
    private final String color;
}
