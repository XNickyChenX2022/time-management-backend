package com.example.timemanagementapp.request.tasks;

import java.sql.Timestamp;
import static com.example.timemanagementapp.utils.TimeUtil.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class GetTasksBetweenTimeRequest {
    @NotNull(message = "Planned start time required")
    private Timestamp plannedStartTime;
    @NotNull(message = "Planned end time required")
    private Timestamp plannedEndTime;

    @AssertTrue(message = "Planned start time must be before planned end time")
    private boolean isValidPlannedTime() {
        return isBefore(plannedStartTime, plannedEndTime);
    }
}
