package com.example.spirngiotbackend.controller;

import com.example.spirngiotbackend.http.HttpResponse;
import com.example.spirngiotbackend.model.Record;
import com.example.spirngiotbackend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrudController {
    private final RecordService recordService;

    @Autowired
    public CrudController(RecordService recordService) {
        this.recordService = recordService;
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

}
