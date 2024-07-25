package com.harun.notiticitionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.harun.notiticitionservice.email.repository")
public class NotiticitionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotiticitionServiceApplication.class, args);
	}

}
