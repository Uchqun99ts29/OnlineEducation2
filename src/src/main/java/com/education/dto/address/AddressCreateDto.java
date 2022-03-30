package com.education.dto.address;

import com.education.dto.Dto;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "childBuilder")
public class AddressCreateDto implements Dto {

    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String homeNumber;
}
