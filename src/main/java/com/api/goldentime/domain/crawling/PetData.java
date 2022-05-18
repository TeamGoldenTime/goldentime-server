package com.api.goldentime.domain.crawling;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetData {

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column
    private String postNum;

    @Column
    private String reportDate;

    @Column
    private String kind;

    @Column
    private String gender;

    @Column
    private String lostPlace;

    @Column
    private String detailLink;

}
