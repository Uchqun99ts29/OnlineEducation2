package com.education.service.education;


import com.education.criteria.EducationCriteria;
import com.education.dto.education.EducationCreateDto;
import com.education.dto.education.EducationDto;
import com.education.dto.education.EducationUpdateDto;
import com.education.dto.response.DataDto;
import com.education.entity.Education;
import com.education.service.GenericCrudService;
import org.springframework.http.ResponseEntity;

public interface EducationService extends GenericCrudService<
        Education,
        EducationDto,
        EducationCreateDto,
        EducationUpdateDto,
        EducationCriteria,
        Long> {
    ResponseEntity<DataDto<Void>> block(Long id);
}
