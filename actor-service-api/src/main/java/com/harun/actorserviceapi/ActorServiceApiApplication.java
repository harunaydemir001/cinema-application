package com.harun.actorserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.harun.actorserviceapi.client"})
@EnableSwagger2
public class ActorServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActorServiceApiApplication.class, args);
    }

}
