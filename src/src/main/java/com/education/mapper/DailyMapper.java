package com.education.mapper;

import com.education.dto.daily.DailyCreateDto;
import com.education.dto.daily.DailyDto;
import com.education.dto.daily.DailyUpdateDto;
import com.education.entity.Daily;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DailyMapper extends BaseMapper<
        Daily,
        DailyDto,
        DailyCreateDto,
        DailyUpdateDto> {
    @Override
    DailyDto toDto(Daily daily);

    @Override
    List<DailyDto> toDto(List<Daily> e);

    @Override
    Daily fromCreateDto(DailyCreateDto dailyCreateDto);

    @Override
    Daily fromUpdateDto(DailyUpdateDto d);
}
