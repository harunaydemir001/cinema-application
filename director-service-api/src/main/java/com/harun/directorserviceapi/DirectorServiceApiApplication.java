package com.harun.directorserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.harun.directorserviceapi.client"})
public class DirectorServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectorServiceApiApplication.class, args);
    }

}
