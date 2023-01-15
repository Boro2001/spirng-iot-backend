package com.example.spirngiotbackend.controller;

import com.example.spirngiotbackend.model.Record;
import com.example.spirngiotbackend.service.RecordService;
import com.example.spirngiotbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class CrudController {

    private final RecordService recordService;
    private final UserService userService;


    @Autowired
    public CrudController(RecordService recordService, UserService userService) {
        this.recordService = recordService;
        this.userService = userService;
    }
    @PostMapping("records")
    public void addRecord(@RequestBody Record record){
        recordService.addRecord(record);
    } // i tak ten endpoint nie powinien istnieć
    @GetMapping("all")
    public List<Record> getAllRecords(){
        return recordService.getAllRecords();
    }
    @GetMapping("user/{userId}/devices")
    public List<String> getDevices(@PathVariable String userId) { return userService.findById(userId).getDevices();}
    @PostMapping("user/{userId}/{deviceId}")
    public void addNewDevice(@PathVariable String userId, @PathVariable String deviceId){
        userService.findById(userId).getDevices().add(deviceId); // trzeba to przetestować
    }
    @GetMapping("user/{userId}/{deviceId}")
    public List<Record> getRecordsForUserForDevice(@PathVariable String userId, @PathVariable String deviceId)
    {   return recordService.getAllRecords().stream()
            .filter(record -> Objects.equals(record.getDeviceId(), deviceId))
            .filter(record -> Objects.equals(record.getUserId(), userId))
            .collect(Collectors.toList());
    }
    @GetMapping("user/{userId}/all")
    public List<Record> getRecordsForUserForDevice(@PathVariable String userId)
    {   return recordService.getAllRecords().stream()
            .filter(record -> Objects.equals(record.getUserId(), userId))
            .collect(Collectors.toList());
    }

}
