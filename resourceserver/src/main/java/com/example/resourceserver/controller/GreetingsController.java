package com.example.resourceserver.controller;

import com.example.resourceserver.service.GreetingsService;
import jakarta.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@ResponseBody
public class GreetingsController {
    private final GreetingsService greetingsService;

    public GreetingsController(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    @GetMapping("/")
    Map<String, String> hello(ServletRequest servletRequest) {
        return this.greetingsService.greet();
    }
}
