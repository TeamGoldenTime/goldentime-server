package com.api.goldentime.web.dto.request.post.lost;


import com.api.goldentime.domain.post.Address;
import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.domain.post.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LostPostSaveRequestDto extends PostSaveRequestDto {

    private int reward;

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
            .color(getColor())
            .reward(getReward())
            .build();
    }

}
