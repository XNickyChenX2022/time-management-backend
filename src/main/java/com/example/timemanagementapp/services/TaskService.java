package com.example.timemanagementapp.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.timemanagementapp.dto.tasks.CreateTaskDTO;
import com.example.timemanagementapp.dto.tasks.GetTasksBetweenTimeDTO;
import com.example.timemanagementapp.dto.tasks.TaskDTO;
import com.example.timemanagementapp.dto.tasks.TasksDTO;
import com.example.timemanagementapp.dto.tasks.UpdateTaskDTO;
import com.example.timemanagementapp.exceptions.TaskNotFoundException;
import com.example.timemanagementapp.model.Task;
import com.example.timemanagementapp.model.User;
import com.example.timemanagementapp.repositories.TaskRepository;
import com.example.timemanagementapp.repositories.UserRepository;
import com.example.timemanagementapp.utils.ModelMapperUtil;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository taskRepository,
            UserService userService,
            UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public TasksDTO getAllTasks() throws UsernameNotFoundException {
        User user = userService.loadAuthenticatedUser();
        List<Task> tasks = taskRepository.findByUser(user);
        return new TasksDTO(ModelMapperUtil.mapAll(tasks, TaskDTO.class));
    }

    public TasksDTO getAllTasks(GetTasksBetweenTimeDTO tasksBetweenTimeDTO)
            throws UsernameNotFoundException {
        User user = userService.loadAuthenticatedUser();
        List<Task> tasks = taskRepository.findByUserAndPlannedStartTimeBetween(user,
                tasksBetweenTimeDTO.getPlannedStartTime(), tasksBetweenTimeDTO.getPlannedEndTime());
        return new TasksDTO(ModelMapperUtil.mapAll(tasks, TaskDTO.class));
    }

    public TaskDTO createTask(CreateTaskDTO CreateTaskDTO) {
        User user = userService.loadAuthenticatedUser();
        Task task = ModelMapperUtil.map(CreateTaskDTO, Task.class);
        user.getTasks().add(task);
        task.setUser(user);
        user = userRepository.save(user);
        task = taskRepository.save(task);
        return ModelMapperUtil.map(task, TaskDTO.class);

    }

    public TaskDTO updateTask(UpdateTaskDTO updateTaskDTO) throws TaskNotFoundException {
        Task task = getTaskByUserAndId(updateTaskDTO.getId());
        ModelMapperUtil.map(updateTaskDTO, task);
        taskRepository.save(task);
        return ModelMapperUtil.map(task, TaskDTO.class);
    }

    public TaskDTO deleteTask(UUID id) throws TaskNotFoundException {
        Task task = getTaskByUserAndId(id);
        taskRepository.deleteById(id);
        return ModelMapperUtil.map(task, TaskDTO.class);
    }

    private Task getTaskByUserAndId(UUID id) throws TaskNotFoundException {
        User user = userService.loadAuthenticatedUser();
        return taskRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new TaskNotFoundException("Task could not be found"));
    }

}
