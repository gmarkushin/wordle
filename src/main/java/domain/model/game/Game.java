package domain.model.game;

import domain.model.attempt.AttemptResoult;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<AttemptResoult> resList;
    private int cnt;
    private String hiddenWord;
    private UUID uid;
    private int maxattempts;
    private boolean gameActive; // Добавляем поле для состояния игры

    public Game(String hiddenWord, int maxattempts){
        this.hiddenWord = hiddenWord;
        this.cnt = 1;
        this.resList = new ArrayList<>();
        this.uid = UUID.randomUUID(); //直接用 UUID.randomUUID()
        this.maxattempts = maxattempts;
        this.gameActive = true; // Игра активна при создании
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

    public List<AttemptResoult> getResList() {
        return resList;
    }

    public void addResList(AttemptResoult attemptResoult){
        resList.add(attemptResoult);
        if (attemptResoult.getStatusCode() == domain.service.Code.WIN_ATTEMPT) {
            this.gameActive = false;
        } else if (cnt >= maxattempts) {
            this.gameActive = false;
        }
    }

    public UUID getUid() {
        return uid;
    }

    public boolean contin(){
        return cnt <= maxattempts && gameActive;
    }

    public boolean isGameActive() {
        return gameActive && cnt <= maxattempts;
    }

    public void finishGame() {
        this.gameActive = false;
    }

    public void cntUp() {
        this.cnt = this.cnt + 1;
    }

    public int getMaxAttempts() {
        return maxattempts;
    }
}