package com.example.spirngiotbackend.controller.configuration;

import com.example.spirngiotbackend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class RecGiver {


    @Bean
    public static RecordService recordService() {
        return new RecordService();
    }
}
