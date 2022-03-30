package com.education.dto.address;

import com.education.dto.GenericDto;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "childBuilder")
public class AddressDto extends GenericDto {
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String homeNumber;

}
