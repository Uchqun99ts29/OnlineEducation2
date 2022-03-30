package com.education.dto.daily;

import com.education.dto.GenericDto;

import lombok.*;


import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyDto extends GenericDto {
    private byte ball;
    private String description;

    private Long subjectId;

    private Date time;

    private Long studentId;

    private boolean isCame;
}
