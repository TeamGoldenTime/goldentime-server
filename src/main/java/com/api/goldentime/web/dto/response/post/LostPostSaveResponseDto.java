package com.api.goldentime.web.dto.response.post;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LostPostSaveResponseDto {

    private final Long id;
    private final String kind;
    private final String color;

    @Builder
    public LostPostSaveResponseDto(Long id, String kind, String color) {
        this.id = id;
        this.kind = kind;
        this.color = color;
    }
}
