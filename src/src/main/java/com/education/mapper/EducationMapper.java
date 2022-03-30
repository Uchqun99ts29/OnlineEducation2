package com.education.mapper;

import com.education.dto.education.EducationCreateDto;
import com.education.dto.education.EducationDto;
import com.education.dto.education.EducationUpdateDto;
import com.education.entity.Education;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",uses = {AddressMapper.class,AuthUserMapper.class})
public interface EducationMapper extends BaseMapper<
        Education,
        EducationDto,
        EducationCreateDto,
        EducationUpdateDto> {
    @Override
    @Mapping(target = "payedUntil",ignore = true)
    EducationDto toDto(Education education);

    @Override
    @Mapping(target = "payedUntil",ignore = true)
    List<EducationDto> toDto(List<Education> e);

    @Override
    @Mapping(target = "payedUntil",ignore = true)
    Education fromCreateDto(EducationCreateDto educationCreateDto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Education fromUpdateDto(EducationUpdateDto d);

}
