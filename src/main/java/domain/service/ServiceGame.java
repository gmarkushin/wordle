package domain.service;

import domain.model.attempt.Attempt;
import domain.model.attempt.AttemptResoult;
import domain.model.game.Game;
import domain.service.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ServiceGame {
    @Autowired
    private Repository dict;

    public String getHiddenWord() {
        return dict.getHiddenWord();
    }

    public boolean isTrueWord(String hiddenWord, String extAtt) {
        return hiddenWord.equals(extAtt);
    }

    public void cntUp(Game game) {
        game.setCnt(game.getCnt() + 1);
    }

    public void addResList(AttemptResoult attemptResoult, Game game){ //норм?
        game.addResList(attemptResoult);
    }

    public void saveGame(Game game) {
        if (game != null) {
            dict.saveGame(game);
        }
    }
}
