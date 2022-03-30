package com.education.dto.payload;

import com.education.dto.Dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadCreateDto implements Dto {
    private Long studentId;
    private Long groupId;
    private int amount;
}
