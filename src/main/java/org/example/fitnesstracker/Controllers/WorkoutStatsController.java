package org.example.fitnesstracker.Controllers;

import lombok.RequiredArgsConstructor;

import org.example.fitnesstracker.DTO.CaloriesProgressDTO;
import org.example.fitnesstracker.DTO.WorkoutStatsDTO;
import org.example.fitnesstracker.Services.WorkoutStatsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class WorkoutStatsController {

    private final WorkoutStatsService statsService;

    @GetMapping("/workouts/by-type")
    public List<WorkoutStatsDTO> getStatsByType(Authentication authentication) {
        return statsService.getStatsByType(authentication.getName());
    }

    @GetMapping("/progress/calories")
    public List<CaloriesProgressDTO> getCaloriesProgress(
            Authentication authentication,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        return statsService.getCaloriesProgress(authentication.getName(), start, end);
    }
}
