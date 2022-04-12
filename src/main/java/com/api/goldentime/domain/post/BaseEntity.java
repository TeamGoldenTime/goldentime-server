package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="dtype")
@Data
@IdClass(PostPK.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "WRITER")
    private User writer;

    @Id
    @Column(name = "POST_DATE")
    private String postDate;

    @Column
    private String zipCode;

    @Column
    private String address;

    @Column
    private String image;

    @Column
    private String region; //laititude, longitude

    @Column
    private String category;

    @Column
    private String kind;

    @Builder
    public BaseEntity(User writer, String postDate, String zipCode, String address, String image, String region, String category, String kind) {
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
