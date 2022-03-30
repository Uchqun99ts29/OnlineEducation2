package com.education.dto.education;


import com.education.dto.GenericDto;
import com.education.dto.address.AddressDto;
import com.education.dto.auth.AuthUserDto;
import com.education.entity.AuthUser;
import lombok.*;

import javax.persistence.ManyToOne;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto extends GenericDto {

    private String logoPath;

    private String phone;

    private String email;

    private boolean isBlock;

    private AddressDto address;

    private String url;

    private String name;

    private String lecithinPath;

    private Date payedUntil;

    private AuthUserDto owner;
}
