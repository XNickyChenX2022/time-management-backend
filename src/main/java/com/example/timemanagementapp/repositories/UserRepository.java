package com.example.timemanagementapp.repositories;

import java.util.Optional;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.example.timemanagementapp.model.User;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findById(UUID id);

    Optional<User> findByUsernameIgnoreCase(String username);

}
