package com.education.service.subject;

import com.education.criteria.GenericCriteria;
import com.education.dto.response.AppErrorDto;
import com.education.dto.response.DataDto;
import com.education.dto.subject.SubjectCreateDto;
import com.education.dto.subject.SubjectDto;
import com.education.dto.subject.SubjectUpdateDto;
import com.education.entity.Education;
import com.education.entity.Subject;
import com.education.exceptions.BadRequestException;
import com.education.mapper.SubjectMapper;
import com.education.repository.EducationRepository;
import com.education.repository.SubjectRepository;
import com.education.service.AbstractService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class SubjectServiceImpl extends AbstractService<SubjectRepository, SubjectMapper> {

    private final EducationRepository educationRepository;

    protected SubjectServiceImpl(SubjectRepository repository,SubjectMapper mapper, EducationRepository educationRepository) {
        super(repository, mapper);
        this.educationRepository = educationRepository;
    }

    public ResponseEntity<DataDto<Long>> create(SubjectCreateDto createDto) {
        String name = createDto.getName().toUpperCase(Locale.ROOT);
        // TODO: 3/28/2022 sessionni education idsi bo'yicha olinadi
        Education education = getEducation(1L);
        if (repository.existsByNameAndEducation(name, education))
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        Subject subject = mapper.fromCreateDto(createDto);
        subject.setEducation(education);
        subject.setName(name);
        subject = repository.save(subject);
        return new ResponseEntity<>(new DataDto<>(subject.getId()), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        Optional<Subject> optional = repository.findByIdAndEducationAndDeletedFalse(id, getEducation(1L));
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        Subject subject = optional.get();
        subject.setDeleted(true);
        repository.save(subject);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Boolean>> update(SubjectUpdateDto updateDto) {
        Optional<Subject> optional = repository.findByIdAndEducationAndDeletedFalse(updateDto.getId(), new Education());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<List<SubjectDto>>> getAll(GenericCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<SubjectDto> dtos = mapper.toDto(repository.findAllByDeletedFalse(request));
        return new ResponseEntity<>(new DataDto<>(dtos), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<List<SubjectDto>>> getAllByEducation(Long id) {
        List<SubjectDto> subjectDtos = mapper.toDto(repository.findAllByEducationAndDeletedFalse(getEducation(id)));
        return new ResponseEntity<>(new DataDto<>(subjectDtos), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<SubjectDto>> get(Long id) {
        Optional<Subject> optional = repository.findById(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().build()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(optional.get())), HttpStatus.OK);
    }

    public Education getEducation(Long id) {
        Optional<Education> optional = educationRepository.findById(id);
        if (optional.isEmpty()) throw new BadRequestException("not found", new Throwable());
        return optional.get();
    }
}
