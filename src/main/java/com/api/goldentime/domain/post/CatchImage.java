package com.api.goldentime.domain.post;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatchImage {

    @Id
    @GeneratedValue()
    @Column(name = "catch_image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "catch_post_id")
    private CatchPost post;

    @Column
    private String name;

    @Column
    private String location;
}
