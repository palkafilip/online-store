package com.fp.onlinestore.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableHystrix
@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
public class CustomersApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }

}
