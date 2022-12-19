package com.zarco.rockpaperscissors.backend.domains.player;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.zarco.rockpaperscissors.backend.domains.player.models.Player;

@Repository
public interface PlayerRepository {
    public Player save(Player gameResult);
    public Optional<Player> findOneByPlayerName(String playerName);
    public void deleteByPlayerName(String playerName);
}
