package com.education.mapper;

import com.education.dto.group.GroupCreateDto;
import com.education.dto.group.GroupDto;
import com.education.dto.group.GroupUpdateDto;
import com.education.entity.Group;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface GroupMapper extends BaseMapper<
        Group,
        GroupDto,
        GroupCreateDto,
        GroupUpdateDto> {
    @Override
    GroupDto toDto(Group group);

    @Override
    List<GroupDto> toDto(List<Group> e);

    @Override
    Group fromCreateDto(GroupCreateDto groupCreateDto);

    @Override
    Group fromUpdateDto(GroupUpdateDto d);
}
