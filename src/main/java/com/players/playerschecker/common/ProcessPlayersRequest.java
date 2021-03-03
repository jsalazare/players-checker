package com.players.playerschecker.common;

import java.util.List;

import com.players.playerschecker.common.dbmodels.Player;

public class ProcessPlayersRequest {


    List<Player> players;


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
