package api.controller;

import api.dto.AttemptResultDTO;
import api.dto.GameDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.attempt.Attempt;
import domain.model.game.Game;
import domain.service.ServiceAttempt;
import domain.service.ServiceGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WordleController {

    @Autowired
    private ServiceGame serviceGame;

    @Autowired
    private ServiceAttempt serviceAttempt;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/game")
    public GameDTO createGame() {
        Game game = serviceGame.createGame();
        GameDTO gameDto = new GameDTO(game);
        return gameDto;
    }

    @GetMapping("/gamelist")
    public List<GameDTO> getAllGames() {  //дичка, не сам придумал, разобрать
        List<Game> games = serviceGame.getAllGames();
        List<GameDTO> gamesDTO = games.stream()
                .map(GameDTO::new)
                .collect(Collectors.toList());
        return gamesDTO;
    }

    @GetMapping("/game/{uuid}")
    public GameDTO getGameUUID(@PathVariable UUID uuid) {
        GameDTO gameDTO = new GameDTO(serviceGame.getGameUUID(uuid)); //сделать ошибку если игра не найдена?? о
        return gameDTO;
    }

    @PostMapping("/game/attempt")
    //@PostMapping("/game/{uuid}/attempt")  @PathVariable UUID uuid //или так??
    public /*<?>*/ GameDTO makeAttempt( //потом подумаю, обсудить как такое делать, try catch и своя ошибка, или через троу пробрасывать
                                                 //мб после попытки возвращать игру целиком?
            @RequestParam("word") String word,
            @RequestParam("gameuuid") UUID gameuuid){
            Game game = serviceGame.getGameUUID(gameuuid);
//            if (game == null) {
//                return new NullPointerException("нет такой игры"); //Каво???
//            }
//            if (!game.isGameActive()) {
//                return new NullPointerException("игра не активна");
//            }

            Attempt attempt = serviceAttempt.createAttempt(word);

            Game updatedGame = serviceAttempt.processAttempt(gameuuid, attempt);

            return new GameDTO(updatedGame);

    }

    @GetMapping("/game/last")
    public GameDTO getLastGame() {
        Game game = serviceGame.getLastGame();
        return new GameDTO(game);
    }
}