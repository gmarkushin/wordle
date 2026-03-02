package domain.service;

import domain.model.attempt.Attempt;
import domain.model.attempt.AttemptResoult;
import domain.model.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ServiceGame {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private WordRepository wordRepository;

    @Value("${count.maxattempt}")
    private int maxattempts;

    public String getHiddenWord() {
        return wordRepository.getRandomWord();
    }

    public Game createGame(){
        Game game = new Game(getHiddenWord(), maxattempts);
        saveGame(game);
        return game;
    }

    public boolean isTrueWord(String hiddenWord, String extAtt) {
        return hiddenWord.equals(extAtt);
    }

    public void saveGame(Game game) {
        if (game != null) {
            gameRepository.saveGame(game);
        }
    }

    public Game getLastGame(){
        return gameRepository.getLastGame();
    }

    public Game getGameUUID(UUID uuid){
        return gameRepository.findByUuid(uuid);
    }

    public List<Game> getAllGames() {
        return gameRepository.gamelist();
    }

}