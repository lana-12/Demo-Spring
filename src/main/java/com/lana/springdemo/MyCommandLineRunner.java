package com.lana.springdemo;

import com.lana.springdemo.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    VilleService villeService;

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(MyCommandLineRunner.class);
        sa.setWebApplicationType(WebApplicationType.NONE);
        sa.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inside Run");
        villeService.loadCsvFromDisk();
    }


}
