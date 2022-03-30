package com.education.service.daily;

import com.education.criteria.DailyCriteria;
import com.education.dto.daily.DailyCreateDto;
import com.education.dto.daily.DailyDto;
import com.education.dto.daily.DailyUpdateDto;
import com.education.dto.response.AppErrorDto;
import com.education.dto.response.DataDto;
import com.education.entity.*;
import com.education.mapper.AuthUserMapper;
import com.education.mapper.DailyMapper;
import com.education.repository.AuthUserRepository;
import com.education.repository.DailyRepository;
import com.education.repository.SubjectRepository;
import com.education.service.AbstractService;
import com.education.service.auth.AuthUserService;
import com.education.service.subject.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DailyServiceImpl extends AbstractService<DailyRepository, DailyMapper> implements DailyService {
    protected final AuthUserService userService;
    protected final SubjectServiceImpl subjectService;
    private final SubjectRepository subjectRepository;
    private final AuthUserRepository userRepository;


    protected DailyServiceImpl(DailyRepository repository, @Qualifier("dailyMapperImpl") DailyMapper mapper, AuthUserService userService, SubjectServiceImpl subjectService, SubjectRepository subjectRepository, AuthUserRepository userRepository) {
        super(repository, mapper);
        this.userService = userService;
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(DailyCreateDto createDto) {
        Daily daily = mapper.fromCreateDto(createDto);
        Optional<Subject> optional = subjectRepository.findById(createDto.getSubjectId());
        Optional<AuthUser> user = userRepository.findById(createDto.getStudentId());
        if (optional.isEmpty() || user.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("User or Subject not found!").build()), HttpStatus.BAD_REQUEST);
        daily.setSubject(optional.get());
        daily.setStudent(user.get());
        daily = repository.save(daily);
        return new ResponseEntity<>(new DataDto<>(daily.getId()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<Void>> delete(Long id) {
        if(get(id)==null){
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("Daily not found").build()), HttpStatus.BAD_REQUEST);
        }
        repository.deleteSoft(id);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(DailyUpdateDto updateDto) {
        Optional<Daily> optional = repository.findById(updateDto.getId());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("Daily not found").build()), HttpStatus.BAD_REQUEST);
        Daily daily = optional.get();
        if (updateDto.getBall()!=null) {
            daily.setBall(updateDto.getBall());
        }
        if (updateDto.getDescription() != null) {
            daily.setDescription(updateDto.getDescription());
        }
        if (updateDto.getSubjectId() > 0) {
            Optional<Subject> optionalSubject = subjectRepository.findById(updateDto.getSubjectId());
            daily.setSubject(optionalSubject.get());
        }
        if(updateDto.getIsCame()!=null){
            daily.setIsCame(updateDto.getIsCame());
        }
        if(updateDto.getTime()!=null){
            daily.setTime(updateDto.getTime());
        }
        repository.save(daily);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<DailyDto>>> getAll(DailyCriteria criteria) {
        List<Daily> dailies = repository.findAll();
        return getAllCheck(dailies);
    }

    private ResponseEntity<DataDto<List<DailyDto>>> getAllCheck(List<Daily> dailies) {
        if(dailies.isEmpty()){
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("You haven't any Daily").build()), HttpStatus.BAD_REQUEST);
        }
        dailies.removeIf(Auditable::isDeleted);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(dailies)), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<List<DailyDto>>> getAllByStudentEducationId(DailyCriteria criteria,Long id){
        List<Daily> dailies=repository.findAllByStudentEducationIdAndDeleted(id,false);
        return getAllCheck(dailies);
    }

    public ResponseEntity<DataDto<List<DailyDto>>> getAllByTime(DailyCriteria criteria, Date date){
        List<Daily> dailies=repository.findAllByTimeAndDeleted(date,false);
        return getAllCheck(dailies);
    }

    @Override
    public ResponseEntity<DataDto<DailyDto>> get(Long id) {
        Optional<Daily> daily = repository.findById(id);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(daily.get())), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<Long>> totalCount(DailyCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }
}
