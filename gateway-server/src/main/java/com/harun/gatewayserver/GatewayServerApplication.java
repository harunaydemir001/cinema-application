package com.harun.gatewayserver;

import com.harun.gatewayserver.constant.RouterConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }


    @Bean
    public RouteLocator cinemaRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/cinema/movie-service/***")
                        .filters(f -> f.rewritePath("/cinema/movie-service/(?<segment>.*)", RouterConstant.SEGMENT)
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://MOVIE-SERVICE"))
                .route(p -> p
                        .path("/cinema/director-service/***")
                        .filters(f -> f.rewritePath("/cinema/director-service/(?<segment>.*)", RouterConstant.SEGMENT)
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://DIRECTOR-SERVICE"))
                .route(p -> p
                        .path("/cinema/actor-service/***")
                        .filters(f -> f.rewritePath("/cinema/actor-service/(?<segment>.*)", RouterConstant.SEGMENT)
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://ACTOR-SERVICE"))
                .build();
    }

}
