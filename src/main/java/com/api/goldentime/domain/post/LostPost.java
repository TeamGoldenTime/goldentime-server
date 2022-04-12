package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;


@Getter
@Entity
public class LostPost extends BaseEntity {

    @Column
    private int reward;

    @Builder
    public LostPost(User writer, String postDate, String zipCode, String address, String image, String region, String category, String kind, int reward) {
        super(writer, postDate, zipCode, address, image, region, category, kind);
        this.reward = reward;
    }
}


