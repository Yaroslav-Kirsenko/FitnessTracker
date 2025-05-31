package org.example.fitnesstracker.Services;


import org.example.fitnesstracker.DTO.WorkoutDTO;
import org.example.fitnesstracker.Models.User;
import org.example.fitnesstracker.Models.Workout;
import org.example.fitnesstracker.Repositories.UserRepository;
import org.example.fitnesstracker.Repositories.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {

    @Mock
    private WorkoutRepository workoutRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private GoalService goalService;

    @InjectMocks
    private WorkoutService workoutService;

    private User user;
    private Workout workout;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        workout = new Workout();
        workout.setId(1L);
        workout.setUser(user);
    }

    @Test
    void getAllWorkoutsForCurrentUser_success() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(workoutRepository.findByUserId(user.getId())).thenReturn(List.of(workout));

        List<Workout> result = workoutService.getAllWorkoutsForCurrentUser(user.getEmail());

        assertEquals(1, result.size());
        verify(workoutRepository).findByUserId(user.getId());
    }

    @Test
    void getWorkoutById_accessDenied() {
        User anotherUser = new User();
        anotherUser.setEmail("wrong@example.com");
        workout.setUser(anotherUser);

        when(workoutRepository.findById(1L)).thenReturn(Optional.of(workout));

        assertThrows(AccessDeniedException.class, () ->
                workoutService.getWorkoutById(1L, user.getEmail()));
    }

    @Test
    void createWorkout_success() {
        WorkoutDTO dto = new WorkoutDTO();
        dto.setType("Running");
        dto.setDuration(30);
        dto.setCalories(300);
        dto.setDate(LocalDateTime.now());


        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(workoutRepository.save(any(Workout.class))).thenReturn(workout);

        Workout result = workoutService.createWorkout(dto, user.getEmail());

        assertNotNull(result);
        verify(goalService).updateGoalsBasedOnWorkout(any(Workout.class));
    }

    @Test
    void deleteWorkout_success() {
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(workout));

        workoutService.deleteWorkout(1L, user.getEmail());

        verify(workoutRepository).delete(workout);
    }

    @Test
    void getUserByEmail_notFound() {
        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->
                workoutService.getAllWorkoutsForCurrentUser("missing@example.com"));
    }
}
