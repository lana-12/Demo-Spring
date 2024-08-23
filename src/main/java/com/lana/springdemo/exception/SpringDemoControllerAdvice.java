package com.lana.springdemo.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpringDemoControllerAdvice {

    @ExceptionHandler({ SpringDemoException.class})
    protected ResponseEntity<String> traiterErreurs(SpringDemoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
