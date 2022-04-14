package com.api.goldentime.domain.post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Column(name = "zipcode", nullable = false)
    private String zipCode;
    @Column(name = "address", nullable = false)
    private String address;

}
