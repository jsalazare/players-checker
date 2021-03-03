package com.players.playerschecker.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.players.playerschecker.common.dbmodels.Player;
import com.players.playerschecker.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {


    private PlayerRepository playerRepository;


    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }




    @Override
    public List<String> processPlayers(List<Player> players) {
        return null;
    }
}