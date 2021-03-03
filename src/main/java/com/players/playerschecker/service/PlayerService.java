package com.players.playerschecker.service;

import java.util.List;

import com.players.playerschecker.common.PlayerDTO;

public interface PlayerService {


    List<String> processPlayers(List<PlayerDTO> players);

}
