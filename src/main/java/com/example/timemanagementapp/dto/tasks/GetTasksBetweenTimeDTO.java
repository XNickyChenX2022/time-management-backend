
package com.example.timemanagementapp.dto.tasks;

import java.sql.Timestamp;

import com.example.timemanagementapp.request.tasks.GetTasksBetweenTimeRequest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class GetTasksBetweenTimeDTO {
    private Timestamp plannedStartTime;
    private Timestamp plannedEndTime;
}