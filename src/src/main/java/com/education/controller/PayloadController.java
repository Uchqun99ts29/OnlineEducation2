package com.education.controller;

import com.education.criteria.PayloadCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.education.dto.payload.PayloadCreateDto;
import com.education.dto.payload.PayloadDto;
import com.education.dto.payload.PayloadUpdateDto;
import com.education.dto.response.DataDto;
import com.education.service.payload.PayloadService;

import java.util.List;

@RestController
public class PayloadController extends AbstractController<PayloadService> {

    public PayloadController(PayloadService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/create", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create(PayloadCreateDto createDto) {
        return service.create(createDto);
    }

    @RequestMapping(value = PATH + "/get-by-student/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<PayloadDto>>> getByStudent(@PathVariable Long id) {
        List<PayloadDto> payloads = service.getByStudent(id);
        if (payloads == null) return new ResponseEntity<>(new DataDto<>(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new DataDto<>(payloads), HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<PayloadDto>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(value = PATH + "/getAll", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<PayloadDto>>> getAll() {
        return service.getAll(new PayloadCriteria());
    }

    @RequestMapping(value = PATH + "/getAll/", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<PayloadDto>>> getAllByEducation() {
        return service.getAllByEducation();
    }

    @RequestMapping(value = PATH + "/update", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<Boolean>> update(PayloadUpdateDto dto) {
        return service.update(dto);
    }

    @RequestMapping(value = PATH + "/delete/{id}/", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }
}
