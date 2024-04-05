package com.example.timemanagementapp.request.tasks;

import java.sql.Timestamp;
import static com.example.timemanagementapp.utils.TimeUtil.*;
import java.util.UUID;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateTaskRequest {
    private UUID id;
    private String taskName;
    private Timestamp plannedStartTime;
    private Timestamp plannedEndTime;
    private Timestamp actualStartTime;
    private Timestamp actualEndTime;
    private String description;

    @AssertTrue(message = "Planned start time must be before planned end time")
    private boolean isValidPlannedTime() {

        return isBefore(plannedStartTime, plannedEndTime);
    }

    @AssertTrue(message = "Planned start time must be before planned end time")
    private boolean isValidActualTime() {
        return isBefore(actualStartTime, actualEndTime);
    }

}
