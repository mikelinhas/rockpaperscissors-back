package com.zarco.rockpaperscissors.backend.domains.player.models;


import lombok.Data;

@Data
public class Player {
    
    private String name;

    public static Player newPlayer(String name) {
        Player player = new Player();
        player.setName(name.toLowerCase());
        return player;
    }

}
