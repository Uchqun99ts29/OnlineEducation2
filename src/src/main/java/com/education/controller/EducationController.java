package com.education.controller;


import com.education.criteria.EducationCriteria;
import com.education.dto.education.EducationCreateDto;
import com.education.dto.education.EducationDto;
import com.education.dto.education.EducationUpdateDto;
import com.education.dto.response.DataDto;
import com.education.service.education.EducationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("education/*")
public class EducationController extends AbstractController<EducationServiceImpl> {


    public EducationController(EducationServiceImpl service) {
        super(service);
    }

    @PostMapping("/create")
    private ResponseEntity<DataDto<Long>> create(@RequestBody EducationCreateDto dto) {
        return service.create(dto);

    }

    @PostMapping("/update")
    private ResponseEntity<DataDto<Long>> update(@RequestBody EducationUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<EducationDto>> get(@PathVariable Long id) {
       return service.get(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<EducationDto>>> getAll(EducationCriteria criteria) {

        return service.getAll(criteria);
    }

    @RequestMapping(value = "/block/{id}", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Void>> block(@PathVariable Long id) {

       return service.block(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Void>> delete(@PathVariable Long id) {
       return service.delete(id);
    }

    @RequestMapping(value = "/payed", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Void>> delete(@RequestBody String data) {
        return new ResponseEntity<>(new DataDto<>(service.payed(data)), HttpStatus.OK);
    }

}
