package com.example.timemanagementapp.response.tasks;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private UUID id;
    private String taskName;
    private Timestamp plannedStartTime;
    private Timestamp plannedEndTime;
    private Timestamp actualStartTime;
    private Timestamp actualEndTime;
    private String description;

}
