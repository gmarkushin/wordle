package domain.service;

import domain.model.attempt.Attempt;
import domain.model.attempt.AttemptResoult;
import domain.model.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceAttempt {

    @Autowired
    private Repository dict;
    private static final char LET_TRUE = '!'; //private + static???
    private static final char LET_FALSE = 'X'; //private + static???
    private static final String NO_CORR_ATT = "Некорректная попытка";
    private static final String WIN_ATT = "Победа!";
    private static final int MAXATTEMPTS = 6;
    private static final String NO_EXISTS = "Неизвестное слово";


    public boolean isWinWord(String hiddenWord, String extAtt) {
        return hiddenWord.equals(extAtt);
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

    public AttemptResoult attemptResoult(Attempt attempt, Game game) {
        AttemptResoult attemptResoult;
        if (!attempt.validAttempt()) {
            attemptResoult = new AttemptResoult(Code.ERR_ATTEMPT, NO_CORR_ATT, game.getCnt(),attempt);
        } else if (isWinWord(game.getHiddenWord(), attempt.getAttempt())) {
            attemptResoult = new AttemptResoult(Code.WIN_ATTEMPT, WIN_ATT, game.getCnt(),attempt);
            game.addResList(attemptResoult);
        } else if(!dict.wordExists(attempt.getAttempt())){ //нужен свой метод в сервисе?
            attemptResoult = new AttemptResoult(Code.NO_EX_WORD, NO_EXISTS, game.getCnt(),attempt);
        } else {
            attemptResoult = new AttemptResoult(Code.COR_ATTEMPT, new String(checkAttempt(game.getHiddenWord(), attempt)),game.getCnt(),attempt);
            game.addResList(attemptResoult);
        }
        return attemptResoult;
    }
}
