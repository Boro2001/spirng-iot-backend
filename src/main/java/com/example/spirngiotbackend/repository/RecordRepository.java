package com.example.spirngiotbackend.repository;

import com.example.spirngiotbackend.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecordRepository extends MongoRepository<Record, String> {
    public List<Record> findByUserId(String username);
    public List<Record> findAllByUserId(String username);
}

