package com.example.spirngiotbackend.repository;

import com.example.spirngiotbackend.model.ERole;
import com.example.spirngiotbackend.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
