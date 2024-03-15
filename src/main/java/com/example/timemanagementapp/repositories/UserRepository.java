package com.example.timemanagementapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.example.timemanagementapp.model.User;
import java.util.List;

// @NoRepositoryBean
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

}
