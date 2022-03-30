package com.education.dto.auth;

import com.education.dto.GenericDto;
import com.education.dto.address.AddressDto;
import com.education.dto.daily.DailyDto;
import com.education.dto.education.EducationDto;
import com.education.dto.payload.PayloadDto;
import com.education.entity.*;
import com.education.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDto extends GenericDto {

    private String fullName;

    private String imagePath;

    private String username;

    private String password;

    private AddressDto address;

    private String email;

    private String phone;

    boolean isBlock;

    private String parentEmail;

    private List<DailyDto> dailies;

    private List<PayloadDto> payloads;

}
