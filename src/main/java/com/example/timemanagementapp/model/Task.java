package com.example.timemanagementapp.model;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String taskName;
    private Timestamp plannedStartTime;
    private Timestamp plannedEndTime;
    private Timestamp ActualStartTime;
    private Timestamp ActualEndTime;
    private String Description;

    public Long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Timestamp getPlannedStartTime() {
        return plannedStartTime;
    }

    public void setPlannedStartTime(Timestamp plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    public Timestamp getPlannedEndTime() {
        return plannedEndTime;
    }

    public void setPlannedEndTime(Timestamp plannedEndTime) {
        this.plannedEndTime = plannedEndTime;
    }

    public Timestamp getActualStartTime() {
        return ActualStartTime;
    }

    public void setActualStartTime(Timestamp actualStartTime) {
        ActualStartTime = actualStartTime;
    }

    public Timestamp getActualEndTime() {
        return ActualEndTime;
    }

    public void setActualEndTime(Timestamp actualEndTime) {
        ActualEndTime = actualEndTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
