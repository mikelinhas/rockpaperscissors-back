package com.zarco.rockpaperscissors.backend.domains.gameresult;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zarco.rockpaperscissors.backend.domains.gameresult.models.GameResult;

@Repository
public interface GameResultRepository {
    public GameResult save(GameResult gameResult);
    public List<GameResult> findAllByPlayerName(String playerName);
    public void deleteAllByPlayerName(String playerName);
}
