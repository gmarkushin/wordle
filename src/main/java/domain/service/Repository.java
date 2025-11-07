package domain.service;

import domain.model.game.Game;

public interface Repository {
    String getHiddenWord();
    boolean wordExists(String word);
    void saveGame(Game game);
}
