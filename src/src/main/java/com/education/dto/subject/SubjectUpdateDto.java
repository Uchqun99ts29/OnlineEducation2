package com.education.dto.subject;

import com.education.dto.GenericDto;
import lombok.*;

@Getter
@Setter
public class SubjectUpdateDto extends GenericDto {
    private String name;
    private Long EducationId;
}
