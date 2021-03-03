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
    public void testPostValidData() throws Exception {

        String content = "{ \"players\": [ { \"name\": \"Sub zero\", \"type\": \"expert\" }, { \"name\": \"Scorpion\", \"type\": \"novice\" }, { \"name\": \"Reptile\", \"type\": \"meh\" } ] }";
        this.mockMvc.perform(post("/player-checker/v1/player/process")
                .contentType("application/json")
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":[\"player Sub zero stored in DB\",\"player Scorpion sent to Kafka topic\",\"player Reptile did not fit\"]}"));

    }

    @Test
    public void testPostInvalidData() throws Exception {

        String content = "{ \"players\": [ { \"name\": \"Sub zero\" }, { \"name\": \"Scorpion\", \"type\": \"novice\" }, { \"name\": \"Reptile\", \"type\": \"meh\" } ] }";
        this.mockMvc.perform(post("/player-checker/v1/player/process")
                .contentType("application/json")
                .content(content))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Invalid player. [Error in object 'processPlayersRequest': codes [playerType.processPlayersRequest,playerType]; arguments []; default message [player.dont.have.type]]"));

    }

}
