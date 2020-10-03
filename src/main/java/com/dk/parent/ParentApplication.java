package com.dk.parent;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParentApplication.class, args);
//        System.out.println("---------SpringBoot application startup begin---------");
        System.out.println("---------SpringBoot application startup end---------");
    }

}
