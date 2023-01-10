package com.example.debeziumtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.debeziumtest")
public class DebeziumTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebeziumTestApplication.class, args);
    }

}
