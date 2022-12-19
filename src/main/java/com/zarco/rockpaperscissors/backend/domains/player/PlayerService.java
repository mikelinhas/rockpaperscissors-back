package com.zarco.rockpaperscissors.backend.domains.player;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zarco.rockpaperscissors.backend.domains.player.models.Player;

@Service
public class PlayerService {
    
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> findOneByPlayerName(String playerName) {
        return playerRepository.findOneByPlayerName(playerName.toLowerCase());
    }

    public void deleteByPlayerName(String playerName) {
        playerRepository.deleteByPlayerName(playerName.toLowerCase());
    }
}
