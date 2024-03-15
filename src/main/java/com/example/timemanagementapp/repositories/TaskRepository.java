package com.example.timemanagementapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.util.List;
import com.example.timemanagementapp.model.Task;
import com.example.timemanagementapp.model.User;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByUser(User user);

    List<Task> findByPlannedStartTimeBetween(Timestamp startOfDay, Timestamp endOfDay);

    List<Task> findByActualStartTimeBetween(Timestamp startOfDay, Timestamp endOfDay);
}
