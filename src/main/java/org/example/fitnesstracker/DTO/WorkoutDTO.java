package org.example.fitnesstracker.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkoutDTO {

    private String type;

    private int duration;

    private int calories;

    private LocalDateTime date;
}
