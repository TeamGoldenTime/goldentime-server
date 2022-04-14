package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;


@Getter
@NoArgsConstructor
@Entity
public class LostPost extends BasePost {

    @Column
    private int reward;

    @Builder
    public LostPost(int id, Address address, Region region, User writer, String image, String category, String kind, String color, int reward) {
        super(id, address, region, writer, image, category, kind, color);
        this.reward = reward;
    }
}


