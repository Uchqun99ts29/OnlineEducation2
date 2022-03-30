package com.education.service;

import com.education.criteria.GenericCriteria;
import com.education.dto.GenericDto;
import com.education.dto.response.DataDto;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;


/**
 * @param <D> -> Dto
 * @param <K> -> class that defines the primary key for your pojo class
 * @param <C> -> Criteria (For Filtering Request)
 */
public interface GenericService<
        D extends GenericDto,
        C extends GenericCriteria,
        K extends Serializable> extends BaseService {
    ResponseEntity<DataDto<List<D>>> getAll(C criteria);

    ResponseEntity<DataDto<D>>  get(K id) throws Exception;

    ResponseEntity<DataDto<Long>> totalCount(C criteria);

}
