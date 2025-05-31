package org.example.fitnesstracker.Controllers;

import org.example.fitnesstracker.Models.Workout;
import org.example.fitnesstracker.Services.WorkoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutController.class)
class WorkoutControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private WorkoutService workoutService;

    @Test
    @WithMockUser(username = "test@example.com")
    void getAllWorkouts_success() throws Exception {
        Workout workout = new Workout();
        workout.setType("Running");

        Mockito.when(workoutService.getAllWorkoutsForCurrentUser("test@example.com"))
                .thenReturn(List.of(workout));

        mockMvc.perform(get("/api/workouts"))
                .andExpect(status().isOk());
    }
}
