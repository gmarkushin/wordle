package api.dto;

import domain.model.attempt.AttemptResoult;
import domain.service.Code;

public class AttemptResultDTO {
    private String attempt;
    private String result;
    private Code status;
    private int attemptNumber;

    public AttemptResultDTO(AttemptResoult attemptResoult) {
        this.attempt = attemptResoult.getAttempt().getAttempt();
        this.result = attemptResoult.getAttemptResoult();
        this.status = attemptResoult.getStatusCode();
        this.attemptNumber = attemptResoult.getCnt();
    }

    public String getAttempt() { return attempt; }
    public void setAttempt(String attempt) { this.attempt = attempt; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public Code getStatus() { return status; }
    public void setStatus(Code status) { this.status = status; }

    public int getAttemptNumber() { return attemptNumber; }
    public void setAttemptNumber(int attemptNumber) { this.attemptNumber = attemptNumber; }
}