package com.example.covidline.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class BaseController {
    @GetMapping("/")
    @PostConstruct
    public String root() {
        return "index";
    }

//    @RequestMapping("/error")
//    public String error() {
//        return "error";
//    }
}
