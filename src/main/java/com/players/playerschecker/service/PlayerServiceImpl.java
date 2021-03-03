package com.players.playerschecker.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.players.playerschecker.common.PlayerDTO;
import com.players.playerschecker.common.dbmodels.Player;
import com.players.playerschecker.messagebroker.KafkaProducer;
import com.players.playerschecker.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {


    private PlayerRepository playerRepository;

    private KafkaProducer kafkaProducer;


    public PlayerServiceImpl(PlayerRepository playerRepository, KafkaProducer kafkaProducer) {
        this.playerRepository = playerRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public List<String> processPlayers(List<PlayerDTO> players) {
        PlayerDTO p = new PlayerDTO();
        p.setName("juan");
        p.setType("novice");
        kafkaProducer.sendMessage(p);
        return null;
    }
}