package com.api.goldentime.web.dto.request.post.lost;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveRequestDto {

    @NotNull
    private Long userId;
    private String zipCode;
    private String address;
    private String image;
    private Double latitude;
    private Double longitude;
    private String category;
    private String kind;
    private String color;
}
