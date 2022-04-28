package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NoArgsConstructor
@AllArgsConstructor
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

}
