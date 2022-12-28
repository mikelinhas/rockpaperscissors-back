package com.zarco.rockpaperscissors.backend.domains.gameresult.models;


import java.util.UUID;

import lombok.Data;

@Data
public class  GameResult {
    
    private String id;
    private String playerName;
    private Symbol playerSymbol = Symbol.NONE;
    private Symbol computerSymbol = Symbol.NONE;
    private Result outcome = Result.NONE;

    public static GameResult newGameResult(GameResultCreationRequest newGameResult) {
        GameResult gameResult = new GameResult();
        gameResult.setId(UUID.randomUUID().toString());
        gameResult.setPlayerName(newGameResult.getPlayerName().toLowerCase());
        gameResult.setPlayerSymbol(newGameResult.getPlayerSymbol());
        gameResult.setComputerSymbol(newGameResult.getComputerSymbol());
        gameResult.setOutcome(newGameResult.getOutcome());
        return gameResult;
    }

}
