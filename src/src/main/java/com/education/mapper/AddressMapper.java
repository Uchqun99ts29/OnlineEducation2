package com.education.mapper;


import com.education.dto.address.AddressCreateDto;
import com.education.dto.address.AddressDto;
import com.education.dto.address.AddressUpdateDto;
import com.education.entity.Address;
import org.springframework.stereotype.Component;

@Component
@org.mapstruct.Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<
        Address,
        AddressDto,
        AddressCreateDto,
        AddressUpdateDto> {

}
