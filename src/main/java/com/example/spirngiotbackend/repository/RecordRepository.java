package com.example.spirngiotbackend.repository;

import com.example.spirngiotbackend.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecordRepository extends MongoRepository<Record, String> {

    @Query("{'id': ?0}")
    List<Record> getRecordsForUser(String username);
}

