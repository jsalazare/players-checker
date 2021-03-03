package com.players.playerschecker.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.players.playerschecker.common.PlayerDTO;

@Component
public class PlayerValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return PlayerDTO.class == clazz;
    }

    @Override
    public void validate(Object object, Errors errors) {

        PlayerDTO player = (PlayerDTO) object;

        if (player.getName() == null) {
            errors.reject("playerName", "player.dont.have.name");
        }

        if (player.getType() == null) {
            errors.reject("playerType", "player.dont.have.type");
        }
    }

    public void validatePlayersList (List<PlayerDTO> players, Errors errors){
        players.forEach(player -> {
            validate(player,errors);
        });
    }
}
