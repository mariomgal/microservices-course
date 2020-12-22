package com.dormakaba.demo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.dormakaba.demo.usercommons.model.entity")
public class SpringbootUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootUserServiceApplication.class, args);
    }

}
