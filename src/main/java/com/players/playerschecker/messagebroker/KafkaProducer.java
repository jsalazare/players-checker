package com.players.playerschecker.messagebroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.players.playerschecker.common.PlayerDTO;

@Service
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${app.topics.novice-players-topic}")
    private String topic;


    @Autowired
    private KafkaTemplate<String, PlayerDTO> kafkaTemplate;

    public void sendMessage(PlayerDTO player) {
        logger.info(String.format("Producing message -> %s", player.toString()));
        this.kafkaTemplate.send(topic, player);
    }
}
