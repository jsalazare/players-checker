package com.players.playerschecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.players.playerschecker.common.dbmodels.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
