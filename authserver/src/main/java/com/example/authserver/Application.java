package com.example.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    var one =
        User.withDefaultPasswordEncoder().roles("admin").username("one").password("pw").build();
    var two =
        User.withDefaultPasswordEncoder().roles("user").username("two").password("pw").build();

    return new InMemoryUserDetailsManager(one, two);
  }
}
