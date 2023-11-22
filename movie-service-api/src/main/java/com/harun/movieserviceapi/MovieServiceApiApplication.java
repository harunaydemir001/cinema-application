package com.harun.movieserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.harun.movieserviceapi.client"})
public class MovieServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApiApplication.class, args);
    }

}
