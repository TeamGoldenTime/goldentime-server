package com.api.goldentime.web.dto.request.post;


import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LostPostSaveRequestDto extends PostSaveRequestDto {

    @NotBlank
    private int reward;

    public LostPostSaveRequestDto(User writer, String postDate, String zipCode, String address, String image,
                                  String region, String category, String kind, int reward) {
        super(writer, postDate, zipCode, address, image, region, category, kind);
        this.reward = reward;
    }

    public LostPost toEntity()
    {
        //dto를 도메인으로 저장
        return LostPost.builder()
                .writer(getWriter())
                .postDate(getPostDate())
                .zipCode(getZipCode())
                .address(getAddress())
                .image(getImage())
                .region(getRegion())
                .category(getCategory())
                .kind(getKind())
                .reward(reward)
                .build();
    }

}
