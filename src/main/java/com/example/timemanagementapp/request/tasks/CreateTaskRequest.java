package com.example.timemanagementapp.request.tasks;

import static com.example.timemanagementapp.utils.TimeUtil.*;

import java.sql.Timestamp;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateTaskRequest {
    @NotBlank(message = "Taskname required")
    @Size(max = 50, message = "Max task length name is 50 characters")
    private String taskName;
    private Timestamp plannedStartTime;
    private Timestamp plannedEndTime;
    private Timestamp actualStartTime;
    private Timestamp actualEndTime;
    private String description = "";

    @AssertTrue(message = "Planned start time must be before planned end time")
    private boolean isValidPlannedTime() {
        return isBefore(plannedStartTime, plannedEndTime);
    }

    @AssertTrue(message = "Planned start time must be before planned end time")
    private boolean isValidActualTime() {
        return isBefore(actualStartTime, actualEndTime);
    }
}
