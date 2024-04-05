package com.example.timemanagementapp.dto.tasks;

import com.example.timemanagementapp.request.tasks.GetTasksBetweenTimeRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

import java.util.UUID;

@Getter
@Setter
@Data
public class TaskDTO {
    private UUID id;
    private String username;
    private String taskName;
    private Timestamp plannedStartTime;
    private Timestamp plannedEndTime;
    private Timestamp actualStartTime;
    private Timestamp actualEndTime;
    private String description;
}
