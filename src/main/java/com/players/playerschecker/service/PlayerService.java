package com.players.playerschecker.service;

import java.util.List;

import com.players.playerschecker.common.dbmodels.Player;

public interface PlayerService {


    List<String> processPlayers(List<Player> players);

}
