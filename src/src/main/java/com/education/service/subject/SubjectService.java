package com.education.service.subject;


import com.education.criteria.GenericCriteria;
import com.education.dto.subject.SubjectCreateDto;
import com.education.dto.subject.SubjectDto;
import com.education.dto.subject.SubjectUpdateDto;
import com.education.entity.Subject;
import com.education.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
public interface SubjectService extends GenericCrudService<
        Subject,
        SubjectDto,
        SubjectCreateDto,
        SubjectUpdateDto,
        GenericCriteria,
        Long> {
}
