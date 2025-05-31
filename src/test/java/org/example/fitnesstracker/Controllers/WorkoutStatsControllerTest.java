package org.example.fitnesstracker.Controllers;

import org.example.fitnesstracker.DTO.WorkoutStatsDTO;
import org.example.fitnesstracker.Services.WorkoutStatsService;
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

@WebMvcTest(WorkoutStatsController.class)
class WorkoutStatsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private WorkoutStatsService statsService;

    @Test
    @WithMockUser(username = "test@example.com")
    void getStatsByType_success() throws Exception {
        Mockito.when(statsService.getStatsByType("test@example.com"))
                .thenReturn(List.of(new WorkoutStatsDTO("Running", 100L, 300L)));

        mockMvc.perform(get("/api/stats/workouts/by-type"))
                .andExpect(status().isOk());
    }
}
