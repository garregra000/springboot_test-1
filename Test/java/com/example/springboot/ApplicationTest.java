package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void unitTest1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=testing")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.post("/delete?post_text=testing"));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("testing"))));
    }

    @Test
    void unitTest2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=hello")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/delete?post_text=Hello").contentType(MediaType.ALL));
        mockMvc.perform(MockMvcRequestBuilders.post("/api?post_input_text=123hello")).andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/delete?post_text=123Hello").contentType(MediaType.ALL));
        mockMvc.perform(MockMvcRequestBuilders.get("/history").contentType(MediaType.ALL))
                .andExpect(content().string(not(containsString("123Hello"))));
    }
}