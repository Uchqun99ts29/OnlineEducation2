package com.education.entity;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder(builderMethodName = "childBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class Address extends Auditable {

    private String country;

    private String city;

    private String street;

    private String houseNumber;

    private String homeNumber;
}
