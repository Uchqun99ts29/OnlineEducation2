package com.education.service.group;

import com.education.criteria.GenericCriteria;
import com.education.dto.group.GroupCreateDto;
import com.education.dto.group.GroupDto;
import com.education.dto.group.GroupUpdateDto;
import com.education.dto.response.AppErrorDto;
import com.education.dto.response.DataDto;
import com.education.entity.AuthUser;
import com.education.entity.Education;
import com.education.entity.Group;
import com.education.enums.Label;
import com.education.enums.Role;
import com.education.mapper.AuthUserMapper;
import com.education.mapper.GroupMapper;
import com.education.repository.AuthUserRepository;
import com.education.repository.EducationRepository;
import com.education.repository.GroupRepository;
import com.education.service.AbstractService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;


@Service
public class GroupServiceImpl extends AbstractService<GroupRepository, GroupMapper> {
    private final EducationRepository educationRepository;
    private final AuthUserRepository authUserRepository;
    private final AuthUserMapper authUserMapper;


    protected GroupServiceImpl(GroupRepository repository, GroupMapper mapper, EducationRepository educationRepository, AuthUserRepository authUserRepository, AuthUserMapper authUserMapper) {
        super(repository, mapper);
        this.educationRepository = educationRepository;
        this.authUserRepository = authUserRepository;
        this.authUserMapper = authUserMapper;
    }

    public ResponseEntity<DataDto<Long>> create(GroupCreateDto createDto) {
        String name = createDto.getName().toUpperCase(Locale.ROOT);
        Optional<Education> optional = educationRepository.findById(createDto.getEducationId());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        Education education = optional.get();
        if (repository.existsByNameAndEducation(name, education))
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);

        AuthUser teacher = getTeacher(createDto.getTeacherId());
        if (Objects.isNull(teacher) || !teacher.getRole().equals(Role.TEACHER))
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        Group group = mapper.fromCreateDto(createDto);
        group.setEducation(optional.get());
        group.setName(name);
        group.setLabel(Label.valueOf(createDto.getLabel()));
        group.setPrice(createDto.getPrice());
        group.setTeacher(teacher);
        group.setStartTime(LocalDateTime.parse(createDto.getStartTime()));
        group = repository.save(group);
        return new ResponseEntity<>(new DataDto<>(group.getId()), HttpStatus.OK);
    }

    private AuthUser getTeacher(Long teacherId) {
        Optional<AuthUser> optional = authUserRepository.findById(teacherId);
        if (optional.isEmpty())
            return null;
        AuthUser teacher=optional.get();
        if (!teacher.getRole().equals(Role.TEACHER))
            return null;
        return teacher;
    }

    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        Optional<Group> optional = repository.findByIdAndEducationAndDeletedFalse(id, new Education());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        Group group = optional.get();
        group.setDeleted(true);
        repository.save(group);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Boolean>> update(GroupUpdateDto updateDto) {
        Optional<Group> optional = repository.findByIdAndEducationAndDeletedFalse(updateDto.getId(), new Education());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        // TODO: 3/25/2022
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<List<GroupDto>>> getAll(GenericCriteria criteria) {
        List<GroupDto> groupDtos = mapper.toDto(repository.findAll());
        return new ResponseEntity<>(new DataDto<>(groupDtos), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<GroupDto>> get(Long id) {
        Optional<Group> optional = repository.findById(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().build()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(optional.get())), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Long>> addStudent(String studentId, String groupId){
        Optional<AuthUser> studentOptional=authUserRepository.findById(Long.valueOf(studentId));

        if (studentOptional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        Optional<Group> groupOptional = repository.findById(Long.valueOf(groupId));
        if (groupOptional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.BAD_REQUEST);
        groupOptional.get().setStudent((List<AuthUser>) studentOptional.get());
        return new ResponseEntity<>(new DataDto<>(studentOptional.get().getId()), HttpStatus.OK);
    }

}
