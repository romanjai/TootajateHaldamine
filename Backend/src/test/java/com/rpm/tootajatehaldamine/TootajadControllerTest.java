package com.rpm.tootajatehaldamine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpm.tootajatehaldamine.controller.TootajadController;
import com.rpm.tootajatehaldamine.model.TootajaRequest;
import com.rpm.tootajatehaldamine.service.DatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TootajadController.class)
class TootajadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatabaseService databaseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetTootajad() throws Exception {
        mockMvc.perform(get("/tootajad/api/getTootajad")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddTootaja_ValidRequest() throws Exception {
        TootajaRequest request = new TootajaRequest(null, "Mati Maasikas", "mati@example.com", "555555", "Tallinn");

        mockMvc.perform(post("/tootajad/api/addTootaja")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void testAddTootaja_InvalidEmail() throws Exception {
        // Vigane email peab tagastama 400 Bad Request
        TootajaRequest request = new TootajaRequest(null, "Mati", "vale-email", "555", "Tallinn");

        mockMvc.perform(post("/tootajad/api/addTootaja")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
