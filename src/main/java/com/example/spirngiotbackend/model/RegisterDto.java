package com.example.spirngiotbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDto {
    private String deviceId;
    private String username;
}
