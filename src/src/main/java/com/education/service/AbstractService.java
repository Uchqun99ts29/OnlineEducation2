package com.education.service;

import com.education.mapper.Mapper;
import com.education.repository.AbstractRepository;


public abstract class AbstractService<R extends AbstractRepository, M extends Mapper> implements BaseService {
   protected R repository;
   protected M mapper;

    protected AbstractService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    }
