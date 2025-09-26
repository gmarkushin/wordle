package domain;

import domain.AttemptResoult;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<AttemptResoult> resList;
    int cnt;
    String hiddenWord;

    public Game(String hiddenWord){
        this.hiddenWord = hiddenWord;
        this.cnt = 1;
        this.resList = new ArrayList<>();
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getCnt() {
        return cnt;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void addResList(AttemptResoult attemptResoult){
        resList.add(attemptResoult);    }
}
