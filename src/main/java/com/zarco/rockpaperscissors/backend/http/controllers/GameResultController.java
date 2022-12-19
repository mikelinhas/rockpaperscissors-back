package com.zarco.rockpaperscissors.backend.http.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zarco.rockpaperscissors.backend.domains.gameresult.GameResultService;
import com.zarco.rockpaperscissors.backend.domains.gameresult.models.GameResult;
import com.zarco.rockpaperscissors.backend.domains.gameresult.models.GameResultCreationRequest;
import com.zarco.rockpaperscissors.backend.domains.gameresult.models.GameResultStats;
import com.zarco.rockpaperscissors.backend.domains.player.PlayerService;
import com.zarco.rockpaperscissors.backend.domains.player.models.Player;

@RestController
@RequestMapping("/game-results")
public class GameResultController {
    
  private final GameResultService gameResultService;
  private final PlayerService playerService;

  GameResultController(GameResultService gameResultService, PlayerService playerService) {
    this.gameResultService = gameResultService;
    this.playerService = playerService;
  }

  @GetMapping("/stats")
  GameResultStats getGameResultsStats(@RequestParam String playerName) {
    Optional<Player> player = playerService.findOneByPlayerName(playerName);
    if(player.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The player does not exist.");
    }
    GameResultStats stats = gameResultService.getGameResultStatsByPlayerName(playerName);
    return stats;
  }

  @PostMapping
  GameResult createGameResult(@RequestBody @Valid GameResultCreationRequest newGameResult) {
    Optional<Player> player = playerService.findOneByPlayerName(newGameResult.getPlayerName());
    if(player.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The player was not created. Please create player before saving the results");
    }
    GameResult gameResult = GameResult.newGameResult(newGameResult);
    return gameResultService.save(gameResult);
  }

}