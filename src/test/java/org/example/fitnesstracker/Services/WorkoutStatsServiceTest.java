// WorkoutStatsServiceTest.java
package org.example.fitnesstracker.Services;

import org.example.fitnesstracker.DTO.CaloriesProgressDTO;
import org.example.fitnesstracker.DTO.WorkoutStatsDTO;
import org.example.fitnesstracker.Repositories.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutStatsServiceTest {

    @Mock private WorkoutRepository workoutRepository;
    @InjectMocks private WorkoutStatsService workoutStatsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStatsByType_success() {
        when(workoutRepository.getStatsByType("test@example.com"))
                .thenReturn(List.of(new WorkoutStatsDTO("Running", 120L, 500L)));

        List<WorkoutStatsDTO> result = workoutStatsService.getStatsByType("test@example.com");
        assertEquals(1, result.size());
    }

    @Test
    void getCaloriesProgress_success() {
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();

        when(workoutRepository.getCaloriesProgress("test@example.com", start, end))
                .thenReturn(List.of(new CaloriesProgressDTO(start.toLocalDate().atStartOfDay(), 500)));

        List<CaloriesProgressDTO> result = workoutStatsService.getCaloriesProgress("test@example.com", start, end);
        assertEquals(1, result.size());
    }
}
