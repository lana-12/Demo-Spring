package com.lana.springdemo.Components;

import org.springframework.context.annotation.Configuration;

/**
 *
 * @author mattb
 */
@Configuration
public class Config {
    public Config(){
        System.out.println("Constructeur de Config");
    }
    public void sayHello(){
        System.out.println("Hello World");
    }
}