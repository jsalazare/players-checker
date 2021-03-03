package com.players.playerschecker.service;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.players.playerschecker.common.PlayerDTO;
import com.players.playerschecker.common.dbmodels.Player;
import com.players.playerschecker.messagebroker.KafkaProducer;
import com.players.playerschecker.repository.PlayerRepository;

@SpringBootTest
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository mockPlayerRepository;

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private PlayerServiceImpl service;

    @Test
    public void testProcessPlayers() {

        List<PlayerDTO> playersList = buildPlayers();

        when(mockPlayerRepository.save(any())).thenReturn(new Player());
        doNothing().when(kafkaProducer).sendMessage(any());

        List<String> resultList = service.processPlayers(playersList);

        verify(kafkaProducer, times(1)).sendMessage(any());
        verify(mockPlayerRepository, times(1)).save(any());
        assertEquals("expected 3 elements in result list",2, resultList.size());

        assertEquals("Not expected message","player Sub zero stored in DB", resultList.get(0));
        assertEquals("Not expected message","player Scorpion sent to Kafka topic", resultList.get(1));

    }

    private List<PlayerDTO> buildPlayers (){
        List<PlayerDTO> playersList = new ArrayList<>();
        PlayerDTO player = new PlayerDTO();
        player.setName("Sub zero");
        player.setType("expert");
        playersList.add(player);

        player = new PlayerDTO();
        player.setName("Scorpion");
        player.setType("novice");

        playersList.add(player);

        return playersList;

    }
}
