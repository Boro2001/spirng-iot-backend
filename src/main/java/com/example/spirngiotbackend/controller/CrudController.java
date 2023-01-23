package com.example.spirngiotbackend.controller;

import com.example.spirngiotbackend.model.Device;
import com.example.spirngiotbackend.model.Record;
import com.example.spirngiotbackend.model.RegisterDto;
import com.example.spirngiotbackend.repository.DeviceRepository;
import com.example.spirngiotbackend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("api/records/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Record> getRecordsForUser(@PathVariable String username){

        return recordService.getAllRecords();
    }

    @PostMapping("api/devices")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void registerDeviceForUser(@RequestBody RegisterDto registerDto){
        Device device = new Device(registerDto.getDeviceId(), registerDto.getUsername());
        if(device.getId()==null) System.out.println("JESTEŚMY ZGUBIENI");
        deviceRepository.save(device);

    }
}
