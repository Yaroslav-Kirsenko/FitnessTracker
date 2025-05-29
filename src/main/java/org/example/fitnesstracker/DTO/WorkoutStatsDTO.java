package org.example.fitnesstracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkoutStatsDTO {

    private String type;
    private long count;
    private Long totalDuration;
    private Long totalCalories;
}

