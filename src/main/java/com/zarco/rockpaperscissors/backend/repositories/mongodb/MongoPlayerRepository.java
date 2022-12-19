package com.zarco.rockpaperscissors.backend.repositories.mongodb;


import static com.mongodb.client.model.Filters.eq;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.zarco.rockpaperscissors.backend.configuration.MongoDBConnection;
import com.zarco.rockpaperscissors.backend.domains.player.PlayerRepository;
import com.zarco.rockpaperscissors.backend.domains.player.models.Player;

@Repository
public class MongoPlayerRepository implements PlayerRepository {

    MongoCollection<Player> collection;

    public MongoPlayerRepository(MongoDBConnection mongoDBConnection) {
        collection = mongoDBConnection.db().getCollection("players", Player.class);
    }

    public Player save(Player player) {
        collection.insertOne(player);
        return player;
    }

    public Optional<Player> findOneByPlayerName(String playerName) {
        Player result = collection.find(eq("name", playerName)).first();
        if(result == null) {
            return Optional.empty();
        }
        return Optional.of(result);
    }


    public void deleteByPlayerName(String playerName) {
        collection.deleteOne(eq("name", playerName));
    }

}
