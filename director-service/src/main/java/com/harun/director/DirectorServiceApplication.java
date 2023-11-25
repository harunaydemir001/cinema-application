package com.harun.director;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.harun.directorserviceapi",
        "com.harun.director"
})
public class DirectorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectorServiceApplication.class, args);
    }

}
