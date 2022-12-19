package com.zarco.rockpaperscissors.backend.domains.gameresult;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zarco.rockpaperscissors.backend.domains.gameresult.models.GameResult;
import com.zarco.rockpaperscissors.backend.domains.gameresult.models.GameResultStats;

@Service
public class GameResultService {
    
    private GameResultRepository gameResultRepository;

    public GameResultService(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
    }

    public GameResult save(GameResult gameResult) {
        return gameResultRepository.save(gameResult);
    }

    public List<GameResult> findAllByPlayerName(String playerName) {
        return gameResultRepository.findAllByPlayerName(playerName);
    }

    public void deleteAllByPlayerName(String playerName) {
        gameResultRepository.deleteAllByPlayerName(playerName.toLowerCase());
    }

    public GameResultStats getGameResultStatsByPlayerName(String playerName) {
        List<GameResult> gameResults = gameResultRepository.findAllByPlayerName(playerName);
        GameResultStats stats = new GameResultStats(playerName);
        for (GameResult result : gameResults) {
            // Sorry for this piece of code ^^
            switch (result.getOutcome()) {
                case Won:
                    stats.gamesWon++;
                    switch (result.getPlayerSymbol()) {
                        case Rock:
                            stats.gamesWonWithRock++;
                            break;

                        case Paper:
                            stats.gamesWonWithPaper++;
                            break;

                        case Scissors:
                            stats.gamesWonWithScissors++;
                            break;

                        case Unknown:
                            break;
                    }
                    break;

                case Lost:
                    stats.gamesLost++;
                    break;

                case Tied:
                    stats.gamesTied++;
                    break;
                
                case Unknown:
                    break;
            }
        }
        return stats;
    }
}
