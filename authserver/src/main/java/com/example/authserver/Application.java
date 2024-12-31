package com.example.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    var pw1 = encoder.encode("pw");
    var pw2 = encoder.encode("pw");
    var user1 = User.withUsername("user1").password(pw1).roles("admin").build();
    var user2 = User.withUsername("user2").password(pw1).roles("user").build();

    var one =
        User.withDefaultPasswordEncoder().roles("admin").username("one").password("pw").build();
    var two =
        User.withDefaultPasswordEncoder().roles("user").username("two").password("pw").build();

    return new InMemoryUserDetailsManager(user1, user2, one, two);
  }

  //favicon not found 404 error was in the console. Now there is a different error.
  @Controller
  static class FaviconController {

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {}
  }
}
