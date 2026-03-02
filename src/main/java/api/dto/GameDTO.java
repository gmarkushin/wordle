package api.dto;

import domain.model.attempt.AttemptResoult;
import domain.model.game.Game;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameDTO {
    private UUID id;
    private int currentAttempt;
    private int maxAttempts;
    private List<AttemptResultDTO> attempts;
    private boolean gameActive;
    private String message;
    private String hiddenWord;

    public GameDTO(Game game) {
        this.id = game.getUid();
        this.currentAttempt = game.getCnt();
        this.maxAttempts = game.getMaxAttempts();
        this.attempts = game.getResList().stream()
                .map(AttemptResultDTO::new)
                .collect(Collectors.toList());
        this.gameActive = game.isGameActive();
        this.hiddenWord = game.getHiddenWord();

        // Устанавливаем сообщение в зависимости от состояния
        if (!gameActive) {
            if (!game.getResList().isEmpty()) {
                AttemptResoult lastAttempt = game.getResList().get(game.getResList().size() - 1);
                if (lastAttempt.getStatusCode() == domain.service.Code.WIN_ATTEMPT) {
                    this.message = "ПОБЕДА!ПОБЕДА!ПОБЕДА!";
                } else {
                    this.message = "ЛУЗ: " + game.getHiddenWord();
                }
            }
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public int getCurrentAttempt() { return currentAttempt; }
    public void setCurrentAttempt(int currentAttempt) { this.currentAttempt = currentAttempt; }

    public int getMaxAttempts() { return maxAttempts; }
    public void setMaxAttempts(int maxAttempts) { this.maxAttempts = maxAttempts; }

    public List<AttemptResultDTO> getAttempts() { return attempts; }
    public void setAttempts(List<AttemptResultDTO> attempts) { this.attempts = attempts; }

    public boolean isGameActive() { return gameActive; }
    public void setGameActive(boolean gameActive) { this.gameActive = gameActive; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getHiddenWord() { return hiddenWord; }
    public void setHiddenWord(String hiddenWord) { this.hiddenWord = hiddenWord; }
}