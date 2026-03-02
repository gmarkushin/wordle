package domain.service;

import domain.model.attempt.AttemptResoult;
import domain.model.game.Game;
import java.util.List;
import java.util.UUID;

public interface GameRepository {
    void saveGame(Game game);
    void addResList(AttemptResoult attemptResoult);
    Game getLastGame();
    Game findByUuid(UUID uuid);
    List<Game> gamelist();
}