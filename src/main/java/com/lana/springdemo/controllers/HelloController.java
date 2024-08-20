package com.lana.springdemo.controllers;

import com.lana.springdemo.services.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    private HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }


    @GetMapping("hello")
    public String hello() {
        return service.salutations();
    }
}
