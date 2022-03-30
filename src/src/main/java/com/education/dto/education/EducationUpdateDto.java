package com.education.dto.education;

import com.education.dto.GenericDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EducationUpdateDto extends GenericDto {

    private String name;


   // private MultipartFile logo;

    private String phone;

    private String email;

    private String url;

    //private MultipartFile lecithin;
}
