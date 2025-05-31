// GoalServiceTest.java
package org.example.fitnesstracker.Services;

import org.example.fitnesstracker.DTO.GoalDTO;
import org.example.fitnesstracker.Models.Goal;
import org.example.fitnesstracker.Models.User;
import org.example.fitnesstracker.Models.Workout;
import org.example.fitnesstracker.Repositories.GoalRepository;
import org.example.fitnesstracker.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoalServiceTest {

    @Mock private GoalRepository goalRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks private GoalService goalService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
    }

    @Test
    void createGoal_success() {
        GoalDTO dto = new GoalDTO();
        dto.setType("calories");
        dto.setTargetValue(500);
        dto.setDeadline(LocalDate.now().plusDays(7).atStartOfDay());
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Goal savedGoal = new Goal();
        savedGoal.setId(1L);
        when(goalRepository.save(any())).thenReturn(savedGoal);

        Goal result = goalService.createGoal(dto, "test@example.com");

        assertNotNull(result);
        verify(goalRepository).save(any());
    }

    @Test
    void updateGoalsBasedOnWorkout_success() {
        Workout workout = new Workout();
        workout.setCalories(200);
        workout.setDuration(30);
        workout.setUser(user);

        Goal goal = new Goal();
        goal.setType("calories");
        goal.setCurrentValue(100);
        goal.setUser(user);

        when(goalRepository.findByUser(user)).thenReturn(List.of(goal));

        goalService.updateGoalsBasedOnWorkout(workout);

        verify(goalRepository).save(goal);
        assertEquals(300, goal.getCurrentValue());
    }
}
