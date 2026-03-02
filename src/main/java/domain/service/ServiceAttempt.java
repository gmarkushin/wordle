package domain.service;

import domain.model.attempt.Attempt;
import domain.model.attempt.AttemptResoult;
import domain.model.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ServiceAttempt {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private GameRepository gameRepository;

    private static final char LET_TRUE = '!';
    private static final char LET_FALSE = 'X';
    private static final String NO_CORR_ATT = "Некорректная попытка";
    private static final String WIN_ATT = "Победа!";
    private static final String NO_EXISTS = "Неизвестное слово";

    @Value("${count.maxletter}")
    private int maxletters;

    public boolean isWinWord(String hiddenWord, String extAtt) {
        return hiddenWord.equals(extAtt);
    }

    public Attempt createAttempt(String word){
        return new Attempt(word, maxletters);
    }

    public char[] checkAttempt(String hiddenWord, Attempt attempt) {
        int len = attempt.getAttempt().length();
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            char currentChar = attempt.getAttempt().charAt(i);
            if (currentChar == hiddenWord.charAt(i)) {
                result[i] = currentChar;
            } else if (hiddenWord.contains(String.valueOf(currentChar))) {
                result[i] = LET_TRUE;
            } else {
                result[i] = LET_FALSE;
            }
        }
        return result;
    }

    public Game processAttempt(UUID gameUuid, Attempt attempt) {
        Game game = gameRepository.findByUuid(gameUuid); //тоже игры может не быть

        if (!game.isGameActive()) { //Заглушка, потом поправить
            return game;
        }

        AttemptResoult attemptResoult;

        if (!attempt.validAttempt()) {
            attemptResoult = new AttemptResoult(Code.ERR_ATTEMPT, NO_CORR_ATT, game.getCnt(), attempt);
        }
        else if (isWinWord(game.getHiddenWord(), attempt.getAttempt())) {
            attemptResoult = new AttemptResoult(Code.WIN_ATTEMPT, WIN_ATT, game.getCnt(), attempt);
            game.addResList(attemptResoult);
            gameRepository.saveGame(game);
            return game;
        }
        else if (!wordRepository.wordExists(attempt.getAttempt())) {
            attemptResoult = new AttemptResoult(Code.NO_EX_WORD, NO_EXISTS, game.getCnt(), attempt);
        }
        else {
            String resultString = new String(checkAttempt(game.getHiddenWord(), attempt));
            attemptResoult = new AttemptResoult(Code.COR_ATTEMPT, resultString, game.getCnt(), attempt);
            game.cntUp();
        }
        game.addResList(attemptResoult);
        gameRepository.saveGame(game); //реп игры в сервисе попытки, некруто, да
        return game;
    }

}