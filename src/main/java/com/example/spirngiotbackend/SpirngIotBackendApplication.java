package com.example.spirngiotbackend;

import com.example.spirngiotbackend.repository.RecordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpirngIotBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpirngIotBackendApplication.class, args);

    }


    // printing all the beans created during init
    @Bean
    public CommandLineRunner run(ApplicationContext appContext) {
        return args -> {

            String[] beans = appContext.getBeanDefinitionNames();
            Arrays.stream(beans).sorted().forEach(System.out::println);

        };
    }
}
