package com.education.dto.daily;

import com.education.dto.GenericDto;
import com.education.entity.Subject;
import lombok.*;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class DailyUpdateDto extends GenericDto {
    private Byte ball;
    private String description;
    private Long subjectId;
    private LocalDate time;
    private Boolean isCame;
}
