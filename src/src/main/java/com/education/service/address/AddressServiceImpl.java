package com.education.service.address;


import com.education.criteria.GenericCriteria;
import com.education.dto.address.AddressCreateDto;
import com.education.dto.address.AddressDto;
import com.education.dto.address.AddressUpdateDto;
import com.education.dto.response.DataDto;
import com.education.mapper.AddressMapper;
import com.education.repository.AddressRepository;
import com.education.service.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl extends AbstractService<AddressRepository, AddressMapper> implements AddressService {

    protected AddressServiceImpl(AddressRepository repository,  AddressMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(AddressCreateDto createDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Void>> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(AddressUpdateDto updateDto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<List<AddressDto>>> getAll(GenericCriteria criteria) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<AddressDto>> get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Long>> totalCount(GenericCriteria criteria) {
        return null;
    }
}
