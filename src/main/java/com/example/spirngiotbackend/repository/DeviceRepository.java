package com.example.spirngiotbackend.repository;

import com.example.spirngiotbackend.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceRepository extends MongoRepository<Device, String> {
}
