package com.example.spirngiotbackend.service;

import com.example.spirngiotbackend.model.Record;
import com.example.spirngiotbackend.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;


    public void addRecord(Record record) {
        System.out.println("siemano");
        recordRepository.save(record);
        System.out.println("siemano1");
    }

    public List<Record> getAllRecords(){
        System.out.println("siemano");
        return recordRepository.findAll();
    }


}
