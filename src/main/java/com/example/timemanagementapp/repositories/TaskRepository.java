package com.example.timemanagementapp.repositories;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.example.timemanagementapp.model.Task;
import com.example.timemanagementapp.model.User;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {

    List<Task> findByUser(User user);

    List<Task> findByUserAndPlannedStartTimeBetween(User user, Timestamp startOfDay, Timestamp endOfDay);

    List<Task> findByUserAndActualStartTimeBetween(User user, Timestamp startOfDay, Timestamp endOfDay);

    Optional<Task> findByUserAndId(User user, UUID id);

}
