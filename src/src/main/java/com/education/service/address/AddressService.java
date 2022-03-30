package com.education.service.address;

import com.education.criteria.GenericCriteria;
import com.education.dto.address.AddressCreateDto;
import com.education.dto.address.AddressDto;
import com.education.dto.address.AddressUpdateDto;
import com.education.entity.Address;
import com.education.service.GenericCrudService;

public interface AddressService extends GenericCrudService<
        Address,
        AddressDto,
        AddressCreateDto,
        AddressUpdateDto,
        GenericCriteria,
        Long> {
}
