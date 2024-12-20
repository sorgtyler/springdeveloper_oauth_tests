package com.example.oauthclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  RouteLocator gateway(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(
            rs ->
                rs.path("/") // wildcards, multiple routes
                    .filters(GatewayFilterSpec::tokenRelay)
                    .uri("http://localhost:8081")) // also definable in yaml?
        .build();
  }
}
