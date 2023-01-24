package com.example.spirngiotbackend.controller;

import com.example.spirngiotbackend.model.Device;
import com.example.spirngiotbackend.model.Record;
import com.example.spirngiotbackend.model.RegisterDto;
import com.example.spirngiotbackend.repository.DeviceRepository;
import com.example.spirngiotbackend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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

    @GetMapping("api/record/{username}/{deviceId}")
    @PreAuthorize("hasRole('USER')")
    public Record getRecordForUser(@PathVariable String username, @PathVariable String deviceId){
        String username1 = " " + username;
        System.out.println(username1);
        List<Record> records = recordService.getAllRecords();
        List<Record> record_sorted;
        // sort records by object id
        record_sorted = records.stream().filter(record -> record.getUserId().equals(username1)).collect(Collectors.toList());

        Record topRecord = record_sorted.get(0);
        for (Record record : record_sorted) {
            if(record.getTimestampAsDate().after(topRecord.getTimestampAsDate())){
                topRecord = record;
            }
        }
        return topRecord;
    }

    @PostMapping("api/devices")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void registerDeviceForUser(@RequestBody RegisterDto registerDto){
        Device device = new Device(registerDto.getDeviceId(), registerDto.getUsername());
        deviceRepository.save(device);

    }
}
