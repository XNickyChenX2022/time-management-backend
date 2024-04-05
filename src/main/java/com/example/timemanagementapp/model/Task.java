package com.example.timemanagementapp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @NotBlank
    private String taskName;
    private Timestamp plannedStartTime;
    private Timestamp plannedEndTime;
    private Timestamp actualStartTime;
    private Timestamp actualEndTime;
    private String description;

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (isEndSetAfterStart(plannedStartTime, plannedEndTime) ||
                (isEndSetAfterStart(actualStartTime, actualEndTime))) {
            throw new IllegalArgumentException("Start time must be before end time");
        }

        if (isEndSetWithoutStart(plannedStartTime, plannedEndTime)) {
            throw new IllegalArgumentException("Planned end time cannot be set without a planned start time");
        }

        if (isEndSetWithoutStart(actualStartTime, actualEndTime)) {
            throw new IllegalArgumentException("Actual end time cannot be set without an actual start time");
        }
    }

    public boolean isEndSetWithoutStart(Timestamp startTime, Timestamp endTime) {
        return startTime == null && endTime != null;
    }

    public boolean isEndSetAfterStart(Timestamp startTime, Timestamp endTime) {
        return startTime != null && endTime != null && startTime.after(endTime);
    }

}
