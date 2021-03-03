package com.players.playerschecker.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPostData() throws Exception {

        String content = "{ \"players\": [ { \"name\": \"Sub zero\", \"type\": \"expert\" }, { \"name\": \"Scorpion\", \"type\": \"novice\" }, { \"name\": \"Reptile\", \"type\": \"meh\" } ] }";
        this.mockMvc.perform(post("/player-checker/v1/player/process")
                .contentType("application/json")
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":[\"player Sub zero stored in DB\",\"player Scorpion sent to Kafka topic\",\"player Reptile did not fit\"]}"));

    }

}
