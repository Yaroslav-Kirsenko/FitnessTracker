package org.example.fitnesstracker.Services;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstracker.DTO.GoalDTO;
import org.example.fitnesstracker.Models.Goal;
import org.example.fitnesstracker.Models.User;
import org.example.fitnesstracker.Models.Workout;
import org.example.fitnesstracker.Repositories.GoalRepository;
import org.example.fitnesstracker.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public List<Goal> getUserGoals(String email) {
        User user = getUserByEmail(email);
        return goalRepository.findByUser(user);
    }

    public Goal getGoalById(Long id, String email) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));
        if (!goal.getUser().getEmail().equals(email)) {
            throw new SecurityException("Access denied");
        }
        return goal;
    }

    public Goal createGoal(GoalDTO goalDTO, String email) {
        User user = getUserByEmail(email);

        Goal goal = Goal.builder()
                .type(goalDTO.getType())
                .targetValue(goalDTO.getTargetValue())
                .currentValue(0)
                .deadline(goalDTO.getDeadline())
                .user(user)
                .build();

        return goalRepository.save(goal);
    }

    public Goal updateGoal(Long id, GoalDTO goalDTO, String email) {
        Goal goal = getGoalById(id, email);
        goal.setType(goalDTO.getType());
        goal.setTargetValue(goalDTO.getTargetValue());
        goal.setDeadline(goalDTO.getDeadline());
        return goalRepository.save(goal);
    }

    public void deleteGoal(Long id, String email) {
        Goal goal = getGoalById(id, email);
        goalRepository.delete(goal);
    }

    public void updateGoalsBasedOnWorkout(Workout workout) {
        User user = workout.getUser();
        List<Goal> userGoals = goalRepository.findByUser(user);

        for (Goal goal : userGoals) {
            String goalType = goal.getType().toLowerCase();
            int valueToAdd = switch (goalType) {
                case "calories" -> workout.getCalories();
                case "duration" -> workout.getDuration();
                case "workouts" -> 1;
                default -> 0;
            };

            if (valueToAdd > 0) {
                goal.setCurrentValue(goal.getCurrentValue() + valueToAdd);
                goalRepository.save(goal);
            }
        }
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
