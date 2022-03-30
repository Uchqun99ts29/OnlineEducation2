package com.education.controller;

import com.education.criteria.AuthUserCriteria;
import com.education.dto.auth.*;
import com.education.dto.response.DataDto;
import com.education.service.auth.AuthUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("auth/*")
public class AuthUserController extends AbstractController<AuthUserServiceImpl> {

    public AuthUserController(AuthUserServiceImpl service) {
        super(service);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> getToken(@RequestBody LoginDto dto) {

        return service.getToken(dto);
    }

    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<DataDto<SessionDto>> getToken(HttpServletRequest request, HttpServletResponse response) {
        return service.refreshToken(request, response);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create( @RequestBody AuthUserCreateDto dto) {

        return service.create(dto);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> update(@RequestBody AuthUserUpdateDto dto) {
        return service.update(dto);

    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Void>> delete(@PathVariable Long id) {
        return service.delete(id);
    }


    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<AuthUserDto>> get(@PathVariable Long id) {

        return service.get(id);
    }


    @RequestMapping(value = "block/super/{id}", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Void>> block(@PathVariable Long id,@RequestParam Long educationId) {

        return service.block(id,educationId);

    }

    @RequestMapping(value = "block/{id}", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Void>> block(@PathVariable Long id) {

        return service.block(id);

    }



}
