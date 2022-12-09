package com.example.covidline.controller.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class APIAuthController {
    
    @GetMapping("/sign-up")
    public String signUp() {
        return "done.";
    }

    @GetMapping("/login")
    public String login() {
        return "done.";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
