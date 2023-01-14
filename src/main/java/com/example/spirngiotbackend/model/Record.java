package com.example.spirngiotbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
@Data
@ToString
@Getter
@AllArgsConstructor
public class Record {

    @Id
    private String id;
    private String deviceId;
    private String userId;
    private int temp;
    private String timestamp;


}
