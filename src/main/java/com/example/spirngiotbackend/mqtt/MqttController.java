package com.example.spirngiotbackend.mqtt;

import com.example.spirngiotbackend.mqtt.MqttPubSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Autowired
    MqttPubSubService service;

    @GetMapping("mqtt/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("mqtt")
    public void sendMessage(String message) {

    }
}
