package com.example.timemanagementapp.dto.tasks;

import java.sql.Timestamp;

import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateTaskDTO {
    private UUID id;
    private String taskName;
    private Timestamp plannedStartTime;
    private Timestamp plannedEndTime;
    private Timestamp actualStartTime;
    private Timestamp actualEndTime;
    private String description;
}
