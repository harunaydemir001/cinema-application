package com.harun.actorserviceapi;

import com.harun.actorserviceapi.client.ActorServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients(clients = ActorServiceClient.class)
@EnableSwagger2
public class ActorServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActorServiceApiApplication.class, args);
	}

}
