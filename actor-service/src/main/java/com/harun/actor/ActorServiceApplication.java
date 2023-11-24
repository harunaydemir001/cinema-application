package com.harun.actor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {
        "com.harun.actorserviceapi",
        "com.harun.actor"
})
@EnableMongoRepositories(basePackages = "com.harun.actor.repository")
@EnableSwagger2
@EnableCaching
public class ActorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActorServiceApplication.class, args);
    }

}
