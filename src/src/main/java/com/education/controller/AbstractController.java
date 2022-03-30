package com.education.controller;

import com.education.service.AbstractService;
import org.springframework.stereotype.Controller;


public class AbstractController<S extends AbstractService> {
    public AbstractController(S service) {
        this.service = service;
    }

    protected S service;
    protected final String API = "/api";
    protected final String VERSION = "v1";
    protected final String PATH = API + VERSION;
}
