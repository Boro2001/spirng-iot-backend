package com.example.spirngiotbackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    public Record(String deviceId, String userId, int temp, String timestamp) {
        this.deviceId = deviceId;
        this.userId = userId;
        this.temp = temp;
        this.timestamp = timestamp;
    }

    @Id
    private String id;
    private String deviceId;
    private String userId;
    private int temp;
    private String timestamp;

    public Date getTimestampAsDate() {
        return new Date(Long.parseLong(timestamp.substring(1)));
    }



}
