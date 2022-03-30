package com.education.controller;

import com.education.criteria.DailyCriteria;
import com.education.dto.daily.DailyCreateDto;
import com.education.dto.daily.DailyDto;
import com.education.dto.daily.DailyUpdateDto;
import com.education.dto.response.DataDto;
import com.education.entity.AuthUser;
import com.education.service.daily.DailyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("daily/*")
public class DailyController extends AbstractController<DailyServiceImpl> {

    public DailyController(DailyServiceImpl service) {
        super(service);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create(@RequestBody DailyCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<Long>> update(@RequestBody DailyUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Void>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<DailyDto>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<DailyDto>>> getAll() {
        return service.getAll(new DailyCriteria());
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<DailyDto>>> getAllByStudentEducationId(AuthUser student) {
        return service.getAllByStudentEducationId(new DailyCriteria(),student.getEducation().getId());
    }

    @RequestMapping(value = "/list/time", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<DailyDto>>> getAllByTime(Date date) {
        return service.getAllByTime(new DailyCriteria(),date);
    }
}
