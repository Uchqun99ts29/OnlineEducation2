package com.education.dto.auth;

import com.education.dto.Dto;
import com.education.dto.address.AddressCreateDto;
import com.education.entity.Address;
import com.education.entity.Education;
import com.education.entity.Language;
import com.education.enums.Role;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserCreateDto implements Dto {

    private String fullName;

    private String imagePath;

    private String username;

    private String password;

    private Long educationId;

    private AddressCreateDto address;


    private String email;

    private String phone;

    private String parentEmail;

}
