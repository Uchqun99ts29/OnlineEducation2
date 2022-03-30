package com.education.mapper;

import com.education.dto.subject.SubjectCreateDto;
import com.education.dto.subject.SubjectDto;
import com.education.dto.subject.SubjectUpdateDto;
import com.education.entity.Subject;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface SubjectMapper extends BaseMapper<
        Subject,
        SubjectDto,
        SubjectCreateDto,
        SubjectUpdateDto> {
    @Override
    SubjectDto toDto(Subject subject);

    @Override
    List<SubjectDto> toDto(List<Subject> e);

    @Override
    Subject fromCreateDto(SubjectCreateDto subjectCreateDto);

    @Override
    Subject fromUpdateDto(SubjectUpdateDto d);

}
