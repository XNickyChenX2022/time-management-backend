package com.example.timemanagementapp.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.timemanagementapp.dto.tasks.CreateTaskDTO;
import com.example.timemanagementapp.dto.tasks.GetTasksBetweenTimeDTO;
import com.example.timemanagementapp.dto.tasks.TaskDTO;
import com.example.timemanagementapp.dto.tasks.TasksDTO;
import com.example.timemanagementapp.dto.tasks.UpdateTaskDTO;
import com.example.timemanagementapp.exceptions.ValidationException;
import com.example.timemanagementapp.request.tasks.CreateTaskRequest;
import com.example.timemanagementapp.request.tasks.GetTasksBetweenTimeRequest;
import com.example.timemanagementapp.request.tasks.UpdateTaskRequest;
import com.example.timemanagementapp.response.tasks.TaskResponse;
import com.example.timemanagementapp.response.tasks.TasksResponse;
import com.example.timemanagementapp.services.TaskService;
import com.example.timemanagementapp.utils.ModelMapperUtil;

import jakarta.validation.Valid;

@RestController
public class TaskController {
    private final TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<TasksResponse> getAllTasks(BindingResult errors) throws ValidationException {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        TasksDTO tasksDTO = taskService.getAllTasks();
        TasksResponse tasksResponse = new TasksResponse();
        tasksResponse.setTaskResponse(ModelMapperUtil.mapAll(tasksDTO.getTaskDTOs(), TaskResponse.class));
        return ResponseEntity.ok(tasksResponse);
    }

    @GetMapping("/tasks/filter")
    public ResponseEntity<TasksResponse> getAllTasksBetweenTwoTimes(
            @Valid @RequestBody GetTasksBetweenTimeRequest getTasksBetweenTimeRequest, BindingResult errors)
            throws ValidationException {
        if (errors.hasErrors()) {
            System.out.println(errors);
            throw new ValidationException(errors);
        }
        GetTasksBetweenTimeDTO tasksBetweenTimeDTO = ModelMapperUtil.map(getTasksBetweenTimeRequest,
                GetTasksBetweenTimeDTO.class);
        TasksDTO tasksDTO = taskService.getAllTasks(tasksBetweenTimeDTO);
        TasksResponse tasksResponse = new TasksResponse();
        tasksResponse.setTaskResponse(ModelMapperUtil.mapAll(tasksDTO.getTaskDTOs(), TaskResponse.class));
        return ResponseEntity.ok(tasksResponse);
    }

    @PostMapping("/task")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest createTaskRequest,
            BindingResult errors) throws ValidationException {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        CreateTaskDTO createTaskDTO = ModelMapperUtil.map(createTaskRequest, CreateTaskDTO.class);
        TaskDTO taskDTO = taskService.createTask(createTaskDTO);
        return ResponseEntity.ok(ModelMapperUtil.map(taskDTO, TaskResponse.class));
    }

    @PutMapping("task/{id}")
    public ResponseEntity<TaskResponse> updateTask(@Valid @PathVariable UUID id,
            @Valid @RequestBody UpdateTaskRequest updateTaskRequest, BindingResult errors) throws ValidationException {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        UpdateTaskDTO updateTaskDTO = ModelMapperUtil.map(updateTaskRequest, UpdateTaskDTO.class);
        updateTaskDTO.setId(id);
        TaskDTO taskDTO = taskService.updateTask(updateTaskDTO);
        return ResponseEntity.ok(ModelMapperUtil.map(taskDTO, TaskResponse.class));
    }

    @DeleteMapping("task/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable UUID id) {
        TaskDTO taskDTO = taskService.deleteTask(id);
        return ResponseEntity.ok(ModelMapperUtil.map(taskDTO, TaskResponse.class));

    }
}
