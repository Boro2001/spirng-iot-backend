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

        recordRepository.save(record);

    }

    public List<Record> getAllRecords(){

        return recordRepository.findAll();
    }

    public List<Record> getAllRecordsForUser(String username){
        return recordRepository.findAllByUserId(username);
    }


}
