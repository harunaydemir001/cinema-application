package com.harun.director;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {
        "com.harun.directorserviceapi",
        "com.harun.director",
        "com.harun.director.annotation",
        "com.harun.common.util"
})
@EnableCaching
public class DirectorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectorServiceApplication.class, args);
    }

}
