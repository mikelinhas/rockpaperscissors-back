package com.zarco.rockpaperscissors.backend.domains.gameresult;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zarco.rockpaperscissors.backend.domains.gameresult.models.GameResult;

@SpringBootTest
public class GameResultRepositoryTest {

    @Autowired
    GameResultRepository gameResultRepository;

    @Test
    void testDeleteAllByPlayerName() {
        // Given
        GameResult gameResult1 = new GameResult();
        gameResult1.setId("result_id_1");
        gameResult1.setPlayerName("player");
        gameResultRepository.save(gameResult1);
        
        // When
        List<GameResult> resultBeforeDeleting = gameResultRepository.findAllByPlayerName("player");
        gameResultRepository.deleteAllByPlayerName("player");
        List<GameResult> resultAfterDeleting = gameResultRepository.findAllByPlayerName("player");

        // Then
        assertThat(resultBeforeDeleting.size()).isEqualTo(1);
        assertThat(resultAfterDeleting.size()).isEqualTo(0);
    }

    @Test
    void testFindAllByPlayerName() {
        // Given
        GameResult gameResult1 = new GameResult();
        gameResult1.setId("result_id_1");
        gameResult1.setPlayerName("player");

        GameResult gameResult2 = new GameResult();
        gameResult2.setId("result_id_2");
        gameResult2.setPlayerName("player");
        
        // When
        gameResultRepository.save(gameResult1);
        gameResultRepository.save(gameResult2);
        List<GameResult> result = gameResultRepository.findAllByPlayerName("player");

        // Then
        assertThat(result.size()).isEqualTo(2);

        // Cleanup
        gameResultRepository.deleteAllByPlayerName("player");
    }

    @Test
    void testSave() {
        // Given
        GameResult gameResult = new GameResult();
        gameResult.setId("result_id");
        gameResult.setPlayerName("player");

        // When
        gameResultRepository.save(gameResult);
        List<GameResult> result = gameResultRepository.findAllByPlayerName("player");
        
        // Then
        assertThat(result.size()).isEqualTo(1);
        
        // Cleanup
        gameResultRepository.deleteAllByPlayerName("player");
    }

}
