package com.api.goldentime.domain.post;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Address {

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "region_1depth_name")
    private String region_1depth_name;

    @Column(name = "region_2depth_name")
    private String region_2depth_name;

    @Column(name = "region_3depth_name")
    private String region_3depth_name;
}
