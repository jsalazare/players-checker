package com.players.playerschecker.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.players.playerschecker.common.Constants;
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
        List<String> result = new ArrayList<>();
        Player newPlayer;
        StringBuilder sb = new StringBuilder();
        for (PlayerDTO player: players){

            switch (player.getType()) {
                case Constants.NOVICE_PLAYER:
                    kafkaProducer.sendMessage(player);
                    sb.append("player ").append(player.getName()).append(" sent to Kafka topic");
                    break;
                case Constants.EXPERT_PLAYER:
                    newPlayer = new Player();
                    newPlayer.setType(player.getType());
                    newPlayer.setName(player.getName());
                    playerRepository.save(newPlayer);
                    sb.append("player ").append(player.getName()).append(" stored in DB");
                    break;
                default:
                    sb.append("player ").append(player.getName()).append(" did not fit");

            }

            result.add(sb.toString());
            sb.setLength(0);

        }

        return result;
    }
}