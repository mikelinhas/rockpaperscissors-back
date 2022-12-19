package com.zarco.rockpaperscissors.backend.domains.gameresult.models;

public class GameResultStats {
    
    public String playerName;
    public Integer gamesWon = 0;
    public Integer gamesLost = 0;
    public Integer gamesTied = 0;
    public Integer gamesWonWithRock = 0;
    public Integer gamesWonWithPaper = 0;
    public Integer gamesWonWithScissors = 0;

    public GameResultStats(String playerName) {
        this.playerName = playerName;
    }

}
