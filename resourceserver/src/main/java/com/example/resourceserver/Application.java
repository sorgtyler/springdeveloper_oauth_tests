package com.example.resourceserver;

import jakarta.servlet.ServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}

@Service
class GreetingsService {

    @PreAuthorize("hasAuthority('SCOPE_user.read')")
    public Map<String, String> greet() {
        var jwt = (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return Map.of("message", "hello " + jwt.getSubject());

    }

}

@RestController
@ResponseBody
class GreetingsController {
    private final GreetingsService greetingsService;

    public GreetingsController(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    @GetMapping("/")
    Map<String, String> hello(ServletRequest servletRequest) {
        return this.greetingsService.greet();
    }
}
