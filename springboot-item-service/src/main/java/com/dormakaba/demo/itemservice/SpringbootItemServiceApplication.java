package com.dormakaba.demo.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootItemServiceApplication.class, args);
    }

}
