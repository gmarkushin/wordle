package domain.model.game;

import domain.model.attempt.AttemptResoult;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Game {
    List<AttemptResoult> resList;
    int cnt;
    String hiddenWord;
    private UUID uid;
    int MAXATTEMPTS = 6;


    public Game(String hiddenWord){
        this.hiddenWord = hiddenWord;
        this.cnt = 1;
        this.resList = new ArrayList<>();
        this.uid = generateUid();
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

    private UUID generateUid() {
        return UUID.randomUUID();
    }

    public UUID getUid() {
            return uid;
    }

    public boolean contin(){
        return cnt<=MAXATTEMPTS;
    }
}
