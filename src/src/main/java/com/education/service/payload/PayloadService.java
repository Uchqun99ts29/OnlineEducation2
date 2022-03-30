package com.education.service.payload;

import com.education.criteria.PayloadCriteria;
import com.education.dto.response.AppErrorDto;
import com.education.dto.response.DataDto;
import com.education.exceptions.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.education.dto.payload.PayloadCreateDto;
import com.education.dto.payload.PayloadDto;
import com.education.dto.payload.PayloadUpdateDto;
import com.education.entity.AuthUser;
import com.education.entity.Group;
import com.education.entity.Payload;
import com.education.mapper.PayloadMapper;
import com.education.repository.AuthUserRepository;
import com.education.repository.GroupRepository;
import com.education.repository.PayloadRepository;
import com.education.service.AbstractService;

import java.util.List;
import java.util.Optional;

@Service
public class PayloadService extends AbstractService<PayloadRepository, PayloadMapper> {

    private final AuthUserRepository userRepository;
    private final GroupRepository groupRepository;

    protected PayloadService(PayloadRepository repository, PayloadMapper mapper, AuthUserRepository userRepository, GroupRepository groupRepository) {
        super(repository, mapper);
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public ResponseEntity<DataDto<Long>> create(PayloadCreateDto createDto) {
        Payload payload = mapper.fromCreateDto(createDto);
        AuthUser user = getUser(createDto.getStudentId());
        Group group = getGroup(createDto.getGroupId());
        if (user == null || group == null)
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("User or Group not found").build()), HttpStatus.BAD_REQUEST);
        payload.setStudent(user);
        payload.setGroup(group);
        payload = repository.save(payload);
        return new ResponseEntity<>(new DataDto<>(payload.getId()), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        Optional<Payload> optional = repository.findById(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("Payload not found").build()), HttpStatus.BAD_REQUEST);
        Payload payload = optional.get();
        payload.setDeleted(true);
        repository.save(payload);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Boolean>> update(PayloadUpdateDto updateDto) {
        Optional<Payload> optional = repository.findById(updateDto.getId());
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("Payload not found").build()), HttpStatus.BAD_REQUEST);
        Payload payload = optional.get();
        payload.setAmount(updateDto.getAmount());
        repository.save(payload);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<DataDto<List<PayloadDto>>> getAll(PayloadCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        Optional<List<Payload>> optional = repository.findAllByDeletedFalse(request);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).build()), HttpStatus.OK);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(optional.get())), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<List<PayloadDto>>> getAllByEducation() {
        Optional<List<Payload>> optional = null;
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("not found").build()), HttpStatus.OK);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(optional.get())), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<PayloadDto>> get(Long id) {
        Optional<Payload> optional = repository.findById(id);
        if (optional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().status(HttpStatus.BAD_REQUEST).message("Payload not found").build()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(optional.get())), HttpStatus.OK);
    }

    public List<PayloadDto> getByStudent(Long id) {
        AuthUser user = getUser(id);
        Optional<List<Payload>> optional = repository.findAllByStudentAndDeletedFalse(user);
        if (optional.isEmpty())
            throw new BadRequestException("not found", new Throwable());
        return mapper.toDto(optional.get());
    }

    private AuthUser getUser(Long id) {
        Optional<AuthUser> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            throw new BadRequestException("not found", new Throwable());
        AuthUser user = userOptional.get();
        if (!user.getRole().name().equals("STUDENT"))
            throw new BadRequestException("not found", new Throwable());
        return user;
    }

    private Group getGroup(Long id) {
        Optional<Group> optional = groupRepository.findById(id);
        if (optional.isEmpty())
            throw new BadRequestException("not found", new Throwable());
        return optional.get();
    }

}
