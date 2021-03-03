package com.players.playerschecker.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.players.playerschecker.common.ProcessPlayersRequest;
import com.players.playerschecker.common.ProcessPlayersResponse;
import com.players.playerschecker.exceptions.InvalidPlayerException;
import com.players.playerschecker.service.PlayerService;

@RestController
@RequestMapping("/player-checker/v1/player")
public class PlayerController {

    private PlayerService playerService;

    private PlayerValidator playerValidator;

    public PlayerController(PlayerService playerService, PlayerValidator playerValidator) {
        this.playerService = playerService;
        this.playerValidator = playerValidator;
    }

    @PostMapping(value="/process")
     public ResponseEntity<ProcessPlayersResponse> processPlayers (@RequestBody ProcessPlayersRequest players, BindingResult bindingResult) throws InvalidPlayerException {
        playerValidator.validatePlayersList(players.getPlayers(), bindingResult);

        if (bindingResult.hasErrors()){
            throw new InvalidPlayerException(bindingResult.getAllErrors());
        }

        ProcessPlayersResponse processPlayersResponse = new ProcessPlayersResponse();
        List<String> resultArray = playerService.processPlayers(players.getPlayers());
        processPlayersResponse.setResult(resultArray);

        return new ResponseEntity<>(processPlayersResponse, HttpStatus.OK);
    }


    @ExceptionHandler(InvalidPlayerException.class)
    public ResponseEntity<String> handleInvalidPlayerException(InvalidPlayerException ex) {
        return new ResponseEntity<String>("Invalid player. " + ex.getErrorList(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}