package domain.model.attempt;

import org.springframework.beans.factory.annotation.Value;

public class Attempt {
    private String attempt;

    private int maxLetters;

    public boolean validAttempt() {
        return attempt != null &&
               attempt.length() == maxLetters &&
               attempt.matches("[а-яё]{5}");
    }

    public String getAttempt() {
        return attempt;
    }

    public Attempt(String extAtt, int maxLetters) {
        this.attempt = extAtt;
        this.maxLetters = maxLetters;
    }
}