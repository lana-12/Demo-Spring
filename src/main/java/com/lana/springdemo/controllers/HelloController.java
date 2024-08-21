package com.lana.springdemo.controllers;

import com.lana.springdemo.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private HelloService helloService;

    public HelloController(HelloService service) {
        this.helloService = service;
    }


    @GetMapping("/hello")
    public String hello() {
        return helloService.salutations();
    }
}
