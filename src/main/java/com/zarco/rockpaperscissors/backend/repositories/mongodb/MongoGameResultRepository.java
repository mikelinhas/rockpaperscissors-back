package com.zarco.rockpaperscissors.backend.repositories.mongodb;


import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.zarco.rockpaperscissors.backend.configuration.MongoDBConnection;
import com.zarco.rockpaperscissors.backend.domains.gameresult.GameResultRepository;
import com.zarco.rockpaperscissors.backend.domains.gameresult.models.GameResult;

@Repository
public class MongoGameResultRepository implements GameResultRepository {

    MongoCollection<GameResult> collection;

    public MongoGameResultRepository(MongoDBConnection mongoDBConnection) {
        collection = mongoDBConnection.db().getCollection("gameResults", GameResult.class);
    }
    public GameResult save(GameResult gameResult) {
        collection.insertOne(gameResult);
        return gameResult;
    }

    public List<GameResult> findAllByPlayerName(String playerName) {
        List<GameResult> gameResults = new ArrayList<>();
        collection.find(eq("playerName", playerName)).into(gameResults);
        return gameResults;
    }

    public void deleteAllByPlayerName(String playerName) {
        collection.deleteMany(eq("playerName", playerName));
    }
}
