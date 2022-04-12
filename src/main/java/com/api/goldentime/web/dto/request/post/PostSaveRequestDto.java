package com.api.goldentime.web.dto.request.post;

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
    private String postDate;
    private String zipCode;
    private String address;
    private String image;
    private String region; //latitude, longitude
    private String category;
    private String kind;

    @Builder
    public PostSaveRequestDto(User writer, String postDate, String zipCode, String address, String image, String region, String category, String kind) {
        this.writer = writer;
        this.postDate = postDate;
        this.zipCode = zipCode;
        this.address = address;
        this.image = image;
        this.region = region;
        this.category = category;
        this.kind = kind;
    }
}
