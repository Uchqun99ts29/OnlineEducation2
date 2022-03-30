package com.education.service.daily;


import com.education.criteria.DailyCriteria;
import com.education.dto.daily.DailyCreateDto;
import com.education.dto.daily.DailyDto;
import com.education.dto.daily.DailyUpdateDto;
import com.education.entity.Daily;
import com.education.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
public interface DailyService extends GenericCrudService<
        Daily,
        DailyDto,
        DailyCreateDto,
        DailyUpdateDto,
        DailyCriteria,
        Long> {
}
