package com.education.dto.payload;

import com.education.dto.GenericDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayloadDto extends GenericDto {
    private String student_fullName;
}
