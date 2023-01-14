package com.example.spirngiotbackend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    private String id;
    private String email;
    private String password;
}
