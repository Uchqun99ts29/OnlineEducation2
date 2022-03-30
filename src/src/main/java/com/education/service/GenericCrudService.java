package com.education.service;

import com.education.criteria.GenericCriteria;
import com.education.dto.Dto;
import com.education.dto.GenericDto;
import com.education.dto.education.EducationDto;
import com.education.dto.response.DataDto;
import com.education.entity.BaseEntity;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 * @param <K>  -> class that defines the primary key for your pojo class
 * @param <C>  -> Criteria (For Filtering Request)
 */
public interface GenericCrudService<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends Dto,
        UD extends GenericDto,
        C extends GenericCriteria,
        K extends Serializable> extends GenericService<D, C, K> {

    ResponseEntity<DataDto<K>>  create(CD createDto);

    ResponseEntity<DataDto<Void>>  delete(K id) throws Exception;

    ResponseEntity<DataDto<Long>>  update(UD updateDto);

}
