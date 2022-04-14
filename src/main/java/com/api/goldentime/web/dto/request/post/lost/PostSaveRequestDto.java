package com.api.goldentime.web.dto.request.post.lost;

import com.api.goldentime.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {

    @NotBlank
    private User writer;
    private String zipCode;
    private String address;
    private String image;
    private Double latitude;
    private Double longitude;
    private String category;
    private String kind;
    private String color;

    @Builder
    public PostSaveRequestDto(@NotBlank User writer, String zipCode, String address, String image, Double latitude, Double longitude, String category, String kind, String color) {
        this.writer = writer;
        this.zipCode = zipCode;
        this.address = address;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.kind = kind;
        this.color = color;
    }
}
