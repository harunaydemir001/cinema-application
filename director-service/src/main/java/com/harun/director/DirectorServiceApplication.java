package com.harun.director;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {
		"com.harun.directorserviceapi",
		"com.harun.director"
})
@EnableSwagger2
@EnableFeignClients
public class DirectorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectorServiceApplication.class, args);
	}

}
