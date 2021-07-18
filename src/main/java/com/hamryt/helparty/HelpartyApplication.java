package com.hamryt.helparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HelpartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpartyApplication.class, args);
    }
    
}