package infrastructure.txtrepository;

import domain.model.attempt.AttemptResoult;
import domain.model.game.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameRepository implements domain.service.GameRepository {
    private final Map<UUID, Game> gameHistory = new HashMap<>();

    private final List<UUID> gameOrder = new ArrayList<>();

    @Value("${path.dictsecretwordtxt}")
    private String dict_secret_path;

    public void saveGame(Game game) {
        UUID uuid = game.getUid();
        if (!gameHistory.containsKey(uuid)) {
            gameOrder.add(uuid);
        }
        gameHistory.put(uuid, game);
    }

    public void addResList(AttemptResoult attemptResoult){
        Game game = getLastGame();
        if (game != null) {
            game.addResList(attemptResoult);
            saveGame(game);
        }
    }

    public Game getLastGame(){
        UUID lastUuid = gameOrder.get(gameOrder.size() - 1);
        return gameHistory.get(lastUuid);
    }

    public Game findByUuid(UUID uuid) {
        return gameHistory.get(uuid);
    }

    public List<Game> gamelist() {
        return new ArrayList<>(gameHistory.values());
    }
}