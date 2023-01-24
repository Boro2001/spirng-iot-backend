package com.example.spirngiotbackend.controller;

import com.example.spirngiotbackend.model.Device;
import com.example.spirngiotbackend.model.Record;
import com.example.spirngiotbackend.model.RegisterDto;
import com.example.spirngiotbackend.repository.DeviceRepository;
import com.example.spirngiotbackend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CrudController {
    private final RecordService recordService;
    private final DeviceRepository deviceRepository;

    @Autowired
    public CrudController(RecordService recordService, DeviceRepository deviceRepository) {
        this.recordService = recordService;
        this.deviceRepository = deviceRepository;
    }
    @PostMapping("api/records")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void addRecord(@RequestBody Record record){
        recordService.addRecord(record);
    }
    @GetMapping("api/records")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Record> getAllRecords(){
        return recordService.getAllRecords();
    }

    @GetMapping("api/record/{username}")
    @PreAuthorize("hasRole('USER')")
    public Record getRecordForUser(@PathVariable String username){
        String username1 = " " + username;
        System.out.println(username1);
        List<Record> records = recordService.getAllRecords();
        records.stream()
            .filter(record -> record.getUserId()
                    .equals(username1)).forEach(record -> System.out.println("FOUND:" + record.getUserId()));
        if(records.stream()
                .filter(record -> record.getUserId()
                        .equals(username1)).toList().size() > 0){
            return records.stream()
                    .filter(record -> record.getUserId()
                            .equals(username1)).toList().get(0);
        }
        return new Record(" "," ",1," ");

    }

    @PostMapping("api/devices")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void registerDeviceForUser(@RequestBody RegisterDto registerDto){
        Device device = new Device(registerDto.getDeviceId(), registerDto.getUsername());
        deviceRepository.save(device);

    }
}
