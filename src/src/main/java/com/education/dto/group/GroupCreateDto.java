package com.education.dto.group;


import com.education.dto.Dto;
import com.education.enums.Label;
import com.fasterxml.jackson.databind.util.LinkedNode;
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
public class GroupCreateDto implements Dto {

    private Long educationId;


    private Long teacherId;

    private String name;

    private String label;

    private Double price;

    private String startTime;


}
