package com.education.service.group;


import com.education.criteria.GenericCriteria;
import com.education.dto.group.GroupCreateDto;
import com.education.dto.group.GroupDto;
import com.education.dto.group.GroupUpdateDto;
import com.education.entity.Group;
import com.education.service.GenericCrudService;

public interface GroupService extends GenericCrudService<
        Group,
        GroupDto,
        GroupCreateDto,
        GroupUpdateDto,
        GenericCriteria,
        Long> {
}
