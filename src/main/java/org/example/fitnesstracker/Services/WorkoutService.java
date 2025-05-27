package org.example.fitnesstracker.Services;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstracker.DTO.WorkoutDTO;
import org.example.fitnesstracker.Repositories.UserRepository;
import org.example.fitnesstracker.Repositories.WorkoutRepository;

import org.example.fitnesstracker.Models.Workout;
import org.example.fitnesstracker.Models.User;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public List<Workout> getAllWorkoutsForCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return workoutRepository.findByUserId(user.getId());
    }

    public Workout getWorkoutById(Long id, String email) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
        if (!workout.getUser().getEmail().equals(email)) {
            throw new AccessDeniedException("Access denied");
        }
        return workout;
    }

    public Workout createWorkout(WorkoutDTO request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Workout workout = new Workout();
        workout.setType(request.getType());
        workout.setDuration(request.getDuration());
        workout.setCalories(request.getCalories());
        workout.setDate(request.getDate());
        workout.setUser(user);
        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(Long id, WorkoutDTO request, String email) {
        Workout workout = getWorkoutById(id, email);
        workout.setType(request.getType());
        workout.setDuration(request.getDuration());
        workout.setCalories(request.getCalories());
        workout.setDate(request.getDate());
        return workoutRepository.save(workout);
    }

    public void deleteWorkout(Long id, String email) {
        Workout workout = getWorkoutById(id, email);
        workoutRepository.delete(workout);
    }





}
