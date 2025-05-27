package org.example.fitnesstracker.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fitnesstracker.DTO.WorkoutDTO;
import org.example.fitnesstracker.Models.User;
import org.example.fitnesstracker.Models.Workout;
import org.example.fitnesstracker.Services.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public List<Workout> getAllWorkouts(Authentication authentication) {
        String email = authentication.getName();
        return workoutService.getAllWorkoutsForCurrentUser(email);
    }

    @GetMapping("/{id}")
    public Workout getWorkoutById(@PathVariable Long id,  Authentication authentication) {
        String email = authentication.getName();
        return workoutService.getWorkoutById(id, email);
    }

    @PostMapping
    public Workout createWorkout(@RequestBody @Valid WorkoutDTO workoutDTO, Authentication authentication) {
        String email = authentication.getName();
        return workoutService.createWorkout(workoutDTO, email);
    }

    @PutMapping("/{id}")
    public Workout updateWorkout(@PathVariable Long id, @RequestBody @Valid WorkoutDTO workoutDTO, Authentication authentication) {
        String email = authentication.getName();
        return workoutService.updateWorkout(id, workoutDTO, email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        workoutService.deleteWorkout(id, email);
        return ResponseEntity.ok().build();
    }
}