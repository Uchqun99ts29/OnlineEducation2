package com.education.mapper;

import com.education.dto.payload.PayloadCreateDto;
import com.education.dto.payload.PayloadDto;
import com.education.dto.payload.PayloadUpdateDto;
import com.education.entity.Payload;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface PayloadMapper  extends BaseMapper<
        Payload,
        PayloadDto,
        PayloadCreateDto,
        PayloadUpdateDto> {
}
