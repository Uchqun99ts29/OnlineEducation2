package com.education.dto.education;

import com.education.dto.Dto;
import com.education.dto.address.AddressCreateDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EducationCreateDto implements Dto {

  // private MultipartFile logo;

    private String phone;

    private String email;

    private AddressCreateDto address;

    private String url;

    private String name;

    //private MultipartFile lecithin;

    private String payedUntil;

    private Long ownerId;

}
