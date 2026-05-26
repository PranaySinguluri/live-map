package com.livemap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.livemap"})
public class LiveMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(LiveMapApplication.class, args);
    }
}