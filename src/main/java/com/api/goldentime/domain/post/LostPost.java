package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;


@Getter
@Entity
public class LostPost extends BasePost {

    @Column
    private int reward;

    @Builder
    public LostPost(Address address, Region region, User user, String image, String category, String kind, int reward) {
        super(address, region, user, image, category, kind);
        this.reward = reward;
    }
}


