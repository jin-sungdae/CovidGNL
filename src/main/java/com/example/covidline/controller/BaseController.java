package com.example.covidline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class BaseController {
    @GetMapping("/")
    @PostConstruct
    public String root() {
        return "index";
    }

}
