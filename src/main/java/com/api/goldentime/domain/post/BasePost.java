package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Data
public abstract class BasePost implements Serializable {

    @Id
    @GeneratedValue()
    private int id;

    @Embedded
    private Address address;

    @Embedded
    private Region region;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @Column
    private String image;

    @Column
    private String category;

    @Column
    private String kind;

    @Column
    private String color;

    @Builder
    public BasePost(int id, Address address, Region region, User writer, String image, String category, String kind, String color) {
        this.id = id;
        this.address = address;
        this.region = region;
        this.writer = writer;
        this.image = image;
        this.category = category;
        this.kind = kind;
        this.color = color;
    }
}
