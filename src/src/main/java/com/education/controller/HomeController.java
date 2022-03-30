package com.education.controller;

import com.education.service.AbstractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class HomeController  {


    @GetMapping("/")
    public String Home() {
        return "Salom";
    }
}
