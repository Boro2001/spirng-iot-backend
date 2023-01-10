package com.example.spirngiotbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Autowired
    MqttPubSubService service;
    @PostMapping("/publish")
    public String publishMessage() {


        return "message published";
    }
}
