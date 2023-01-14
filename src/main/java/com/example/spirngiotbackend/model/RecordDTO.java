package com.example.spirngiotbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RecordDTO {
    private String id;
    private String deviceId;
    private String userId;
    private int temp;
    private String timestamp;

    public RecordDTO(String id, String deviceId, String userId, int temp, String timestamp) {
        this.id = id;
        this.deviceId = deviceId;
        this.userId = userId;
        this.temp = temp;
        this.timestamp = timestamp;
    }
}
