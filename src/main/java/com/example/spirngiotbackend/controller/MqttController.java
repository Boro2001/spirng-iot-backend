package com.example.spirngiotbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class MqttController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}

