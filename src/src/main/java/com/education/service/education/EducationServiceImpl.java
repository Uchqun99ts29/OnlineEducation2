package com.education.service.education;


import com.education.criteria.EducationCriteria;
import com.education.dto.education.EducationCreateDto;
import com.education.dto.education.EducationDto;
import com.education.dto.education.EducationUpdateDto;
import com.education.dto.response.AppErrorDto;
import com.education.dto.response.DataDto;
import com.education.entity.Education;
import com.education.exceptions.AppError;
import com.education.mapper.AddressMapper;
import com.education.mapper.EducationMapper;
import com.education.repository.AddressRepository;
import com.education.repository.EducationRepository;
import com.education.service.AbstractService;
import com.education.service.FileStorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class EducationServiceImpl extends AbstractService<EducationRepository, EducationMapper> implements EducationService {

    private final FileStorageService fileStorageService;

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    protected EducationServiceImpl(EducationRepository repository, EducationMapper mapper, FileStorageService fileStorageService, AddressRepository addressRepository, AddressMapper addressMapper) {
        super(repository, mapper);
        this.fileStorageService = fileStorageService;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }


    @Override
    public ResponseEntity<DataDto<Long>> create(EducationCreateDto createDto) {

        Education education = mapper.fromCreateDto(createDto);
        addressRepository.save(education.getAddress());

        // education.setAddress(addressRepository.save(addressMapper.fromCreateDto(createDto.getAddress())));
        //education.setLecithinPath(fileStorageService.store(createDto.getLecithin()));
        //  education.setLogoPath(fileStorageService.store(createDto.getLogo()));
        return new ResponseEntity<>(new DataDto<>(repository.save(education).getId()), HttpStatus.OK);


    }


    @Override
    public ResponseEntity<DataDto<Void>> delete(Long id) {
        Education education = repository.getNotDelete(id);
        education.setDeleted(true);
        repository.save(education);

        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<DataDto<Long>> update(EducationUpdateDto updateDto) {


        repository.getById(updateDto.getId());

        Education education = mapper.fromUpdateDto(updateDto);
        Education education1 = repository.save(education);

        return new ResponseEntity<>(new DataDto<>(education1.getId()), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<DataDto<List<EducationDto>>> getAll(EducationCriteria criteria) {


        List<Education> all = repository.getAll(criteria.getPage(), criteria.getSize());

        return new ResponseEntity<>(new DataDto<>(mapper.toDto(all)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<EducationDto>> get(Long id) {
        Education education = repository.getNotDelete(id);
        if (Objects.isNull(education)) {
            throw new NotFoundException("education not found");
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(education)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<DataDto<Long>> totalCount(EducationCriteria criteria) {

        return null;
    }

    public ResponseEntity<DataDto<Void>> block(Long id) {

        Education education = repository.getNotDelete(id);
        if (Objects.isNull(education)) {
            throw new NotFoundException("Education not found");
        }
        education.setBlock(!education.isBlock());
        repository.save(education);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);


    }

    public Void payed(String data) {
        Date date = new Date();
        return null;
    }
}
