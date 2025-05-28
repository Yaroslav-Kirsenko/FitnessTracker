package org.example.fitnesstracker.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class GoalDTO {

    private String type;

    private int targetValue;

    private LocalDateTime deadline;
}
