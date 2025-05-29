package org.example.fitnesstracker.Repositories;

import org.example.fitnesstracker.DTO.CaloriesProgressDTO;
import org.example.fitnesstracker.DTO.WorkoutStatsDTO;
import org.example.fitnesstracker.Models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);

    @Query("""
    SELECT new org.example.fitnesstracker.DTO.WorkoutStatsDTO(
        w.type, COUNT(w), SUM(w.duration), SUM(w.calories)
    )
    FROM Workout w
    WHERE w.user.email = :email
    GROUP BY w.type
""")
    List<WorkoutStatsDTO> getStatsByType(@Param("email") String email);

    @Query("""
    SELECT new org.example.fitnesstracker.DTO.CaloriesProgressDTO(
        w.date, SUM(w.calories)
    )
    FROM Workout w
    WHERE w.user.email = :email AND w.date BETWEEN :start AND :end
    GROUP BY w.date
    ORDER BY w.date
""")
    List<CaloriesProgressDTO> getCaloriesProgress(
            @Param("email") String email,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );



}