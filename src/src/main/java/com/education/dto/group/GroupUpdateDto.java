package com.education.dto.group;

import com.education.dto.GenericDto;
import com.education.enums.Label;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class GroupUpdateDto extends GenericDto {

    private Long educationId;

    private Long teacherId;

    private String name;

    private String label;

    private Double price;

    private String startTime;
}
