package com.example.timemanagementapp.dto.tasks;

import java.util.List;

import com.example.timemanagementapp.model.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTO {
    private List<TaskDTO> taskDTOs;
}
