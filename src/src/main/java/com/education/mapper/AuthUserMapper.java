package com.education.mapper;


import com.education.dto.auth.AuthUserCreateDto;
import com.education.dto.auth.AuthUserDto;
import com.education.dto.auth.AuthUserUpdateDto;
import com.education.entity.AuthUser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring",uses = {AddressMapper.class,DailyMapper.class,PayloadMapper.class})
public interface AuthUserMapper extends BaseMapper <
        AuthUser,
        AuthUserDto,
        AuthUserCreateDto,
        AuthUserUpdateDto>{
    @Override
    AuthUserDto toDto(AuthUser authUser);

    @Override
    List<AuthUserDto> toDto(List<AuthUser> e);

    @Override
    AuthUser fromCreateDto(AuthUserCreateDto authUserCreateDto);

    @Override
    AuthUser fromUpdateDto(AuthUserUpdateDto d);
}
