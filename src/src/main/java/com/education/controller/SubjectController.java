package com.education.controller;

import com.education.criteria.GenericCriteria;
import com.education.dto.response.DataDto;
import com.education.dto.subject.SubjectCreateDto;
import com.education.dto.subject.SubjectDto;
import com.education.dto.subject.SubjectUpdateDto;
import com.education.service.subject.SubjectServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubjectController extends AbstractController<SubjectServiceImpl> {
    public SubjectController(SubjectServiceImpl service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/subject/create", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody SubjectCreateDto dto) {
        return service.create(dto);
    }

    @RequestMapping(value = PATH + "/subject/update", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<Boolean>> update(@RequestBody SubjectUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = PATH + "/subject/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = PATH + "/subject/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<SubjectDto>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(value = PATH + "/subject/list", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<SubjectDto>>> getAll(@RequestBody GenericCriteria criteria) {
        return service.getAll(criteria);
    }

    @RequestMapping(value = PATH + "/subject/list/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<SubjectDto>>> getAllByEducation(@PathVariable Long id) {
        return service.getAllByEducation(id);
    }

}
