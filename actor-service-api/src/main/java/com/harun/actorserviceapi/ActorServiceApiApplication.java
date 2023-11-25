package com.harun.actorserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.harun.actorserviceapi.client"})
public class ActorServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActorServiceApiApplication.class, args);
    }

}
