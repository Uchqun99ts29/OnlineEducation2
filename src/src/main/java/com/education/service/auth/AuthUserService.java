package com.education.service.auth;


import com.education.criteria.AuthUserCriteria;
import com.education.dto.auth.AuthUserCreateDto;
import com.education.dto.auth.AuthUserDto;
import com.education.dto.auth.AuthUserUpdateDto;
import com.education.entity.AuthUser;
import com.education.service.GenericCrudService;

public interface AuthUserService extends GenericCrudService<
        AuthUser,
        AuthUserDto,
        AuthUserCreateDto,
        AuthUserUpdateDto,
        AuthUserCriteria,
        Long> {
        }
