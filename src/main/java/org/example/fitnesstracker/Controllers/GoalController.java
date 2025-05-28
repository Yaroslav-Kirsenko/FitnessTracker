package org.example.fitnesstracker.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.fitnesstracker.DTO.GoalDTO;
import org.example.fitnesstracker.Models.Goal;
import org.example.fitnesstracker.Services.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping
    public List<Goal> getUserGoals(Authentication authentication) {
        return goalService.getUserGoals(authentication.getName());
    }

    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id, Authentication authentication) {
        return goalService.getGoalById(id, authentication.getName());
    }

    @PostMapping
    public Goal createGoal(@RequestBody GoalDTO goalDTO, Authentication authentication) {
        return goalService.createGoal(goalDTO, authentication.getName());
    }

    @PutMapping("/{id}")
    public Goal updateGoal(@PathVariable Long id, @RequestBody GoalDTO goalDTO, Authentication authentication) {
        return goalService.updateGoal(id, goalDTO, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long id, Authentication authentication) {
        goalService.deleteGoal(id, authentication.getName());
        return ResponseEntity.ok().build();
    }
}
