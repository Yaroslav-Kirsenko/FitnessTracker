package org.example.fitnesstracker.Controllers;

import org.example.fitnesstracker.Models.Goal;
import org.example.fitnesstracker.Services.GoalService;
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

@WebMvcTest(GoalController.class)
class GoalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private GoalService goalService;

    @Test
    @WithMockUser(username = "test@example.com")
    void getUserGoals_success() throws Exception {
        Goal goal = new Goal();
        goal.setType("calories");
        goal.setTargetValue(500);

        Mockito.when(goalService.getUserGoals("test@example.com")).thenReturn(List.of(goal));

        mockMvc.perform(get("/api/goals"))
                .andExpect(status().isOk());
    }
}