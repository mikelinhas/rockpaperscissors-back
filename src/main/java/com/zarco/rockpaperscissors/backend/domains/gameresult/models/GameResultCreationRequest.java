package com.zarco.rockpaperscissors.backend.domains.gameresult.models;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GameResultCreationRequest {
    
    @NotNull
    private Symbol playerSymbol;

    @NotNull
    private Symbol adversarySymbol;

    @NotNull
    private Result outcome;

    @NotNull
    private String playerName;

}
