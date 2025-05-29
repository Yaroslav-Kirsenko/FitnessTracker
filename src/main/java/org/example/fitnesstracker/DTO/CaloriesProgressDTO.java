package org.example.fitnesstracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CaloriesProgressDTO {

    private LocalDateTime date;

    private long totalCalories;

}
