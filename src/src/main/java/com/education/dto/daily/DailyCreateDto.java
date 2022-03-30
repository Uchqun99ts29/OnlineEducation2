package com.education.dto.daily;

import com.education.dto.Dto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor

public class DailyCreateDto implements Dto {

    private byte ball;

    private String description;

    private Long subjectId;

    private Date time;

    private Long studentId;

    private boolean isCame;
}
