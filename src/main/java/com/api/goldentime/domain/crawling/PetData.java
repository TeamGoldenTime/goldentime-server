package com.api.goldentime.domain.crawling;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetData {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
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
    private String remark;

    @Column
    private String detailLink;

}
