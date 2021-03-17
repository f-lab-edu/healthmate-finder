package com.hamryt.helparty;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.hamryt.helparty.mapper")
@EnableCaching
@SpringBootApplication
public class HelpartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpartyApplication.class, args);
    }


}