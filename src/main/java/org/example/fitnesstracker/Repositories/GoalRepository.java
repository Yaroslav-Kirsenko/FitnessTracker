package org.example.fitnesstracker.Repositories;

import org.example.fitnesstracker.Models.Goal;
import org.example.fitnesstracker.Models.User;
import org.example.fitnesstracker.Models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUser(User user);
}
