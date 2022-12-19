package com.zarco.rockpaperscissors.backend.http.controllers;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zarco.rockpaperscissors.backend.domains.gameresult.GameResultService;
import com.zarco.rockpaperscissors.backend.domains.player.PlayerService;
import com.zarco.rockpaperscissors.backend.domains.player.models.Player;

@RestController
@RequestMapping("/players")
public class PlayerController {
    
  private final PlayerService playerService;
  private final GameResultService gameResultService;

  PlayerController(PlayerService playerService, GameResultService gameResultService) {
    this.playerService = playerService;
    this.gameResultService = gameResultService;
  }

  @GetMapping("/{playerName}")
  Player getPlayerByName(@PathVariable String playerName) {
    Optional<Player> player = playerService.findOneByPlayerName(playerName);
    if(player.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This player does not exist");
    }
    return player.get();
  }

  @PostMapping
  Player createPlayer(@RequestBody @Valid String playerName) {
    Optional<Player> player = playerService.findOneByPlayerName(playerName);
    if(player.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "This player already exists");
    }
    Player createdPlayer = Player.newPlayer(playerName);
    return playerService.save(createdPlayer);
  }

  @DeleteMapping("/{playerName}/reset")
  void deletePlayer(@PathVariable String playerName) {
    Optional<Player> player = playerService.findOneByPlayerName(playerName);
    if(player.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This player does not exist");
    }
    playerService.deleteByPlayerName(playerName);
  };

  @PostMapping("/{playerName}/reset")
  void resetPlayer(@PathVariable String playerName) {
    Optional<Player> player = playerService.findOneByPlayerName(playerName);
    if(player.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This player does not exist");
    }
    gameResultService.deleteAllByPlayerName(playerName);
  };

}