package com.tht.api.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping ("/hello")
    public String hello() {
        return "Hello world";
    }

    @PostMapping
    public String sendkite() {

    }
}
