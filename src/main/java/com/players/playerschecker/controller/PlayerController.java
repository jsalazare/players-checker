package com.players.playerschecker.controller;


import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.players.playerschecker.common.ProcessPlayersRequest;
import com.players.playerschecker.common.ProcessPlayersResponse;
import com.players.playerschecker.service.PlayerService;

@RestController
@RequestMapping("/v1/player")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(value="**")
     public ProcessPlayersResponse processPlayers (@RequestBody ProcessPlayersRequest players) {

        ProcessPlayersResponse processPlayersResponse = new ProcessPlayersResponse();
        List<String> resultArray = playerService.processPlayers(players.getPlayers());
        processPlayersResponse.setResult(resultArray);
        return processPlayersResponse;
    }

}
