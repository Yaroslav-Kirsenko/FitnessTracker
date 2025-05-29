package org.example.fitnesstracker.Services;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstracker.DTO.CaloriesProgressDTO;
import org.example.fitnesstracker.DTO.WorkoutStatsDTO;
import org.example.fitnesstracker.Repositories.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutStatsService {

    private final WorkoutRepository workoutRepository;

    public List<WorkoutStatsDTO> getStatsByType(String email) {
        return workoutRepository.getStatsByType(email);
    }

    public List<CaloriesProgressDTO> getCaloriesProgress(String email, LocalDateTime start, LocalDateTime end) {
        return workoutRepository.getCaloriesProgress(email, start, end);
    }
}
