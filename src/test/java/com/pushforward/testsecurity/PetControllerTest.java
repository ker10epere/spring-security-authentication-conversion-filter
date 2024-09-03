package com.pushforward.testsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PetController.class)
@Import(SecurityConfig.class)
class PetControllerTest {
    @Autowired
    private MockMvc mvc;

    @WithMockUser("ker")
    @Test
    void pet() throws Exception {
        mvc.perform(get("/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-TOKEN", "eyJ1c2VySWQiOiJhYmNkMTIzIiwiZXhwaXJ5IjoxNjQ2NjM1NjExMzAxfQ"))
                .andExpect(status().isOk());
    }
}