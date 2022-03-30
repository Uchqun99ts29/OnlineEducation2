package com.education.dto.payload;


import com.education.dto.GenericDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadUpdateDto extends GenericDto {
    private int amount;
}
