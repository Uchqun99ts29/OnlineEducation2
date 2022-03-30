package com.education.dto.subject;

import com.education.dto.Dto;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SubjectCreateDto implements Dto {

    @NotBlank
    private String name;

    private String description;
}
