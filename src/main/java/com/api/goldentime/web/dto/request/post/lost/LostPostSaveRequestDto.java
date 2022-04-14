package com.api.goldentime.web.dto.request.post.lost;


import com.api.goldentime.domain.post.Address;
import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.domain.post.Region;
import com.api.goldentime.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LostPostSaveRequestDto extends PostSaveRequestDto {

    @NotBlank
    private int reward;

    public LostPostSaveRequestDto(@NotBlank User writer, String zipCode, String address, String image,
                                  Double latitude, Double longitude, String category, String kind, @NotBlank int reward) {

        //super(writer, zipCode, address, image, latitude, longitude, category, kind);
        this.reward = reward;
    }

    public LostPost toEntity()
    {
        Address address = new Address(getZipCode(), getAddress());
        Region region = new Region(getLatitude(), getLongitude());

        return LostPost.builder()
                .address(address)
                .region(region)
                .image(getImage())
                .category(getCategory())
                .kind(getKind())
                .reward(reward)
                .build();
    }

}
