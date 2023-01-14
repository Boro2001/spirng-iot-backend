package com.example.spirngiotbackend.repository;

import com.example.spirngiotbackend.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecordRepository extends MongoRepository<Record, String> {}

