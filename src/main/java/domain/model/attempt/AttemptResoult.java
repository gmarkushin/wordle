package domain.model.attempt;

import domain.service.Code;

public class AttemptResoult {
    private Attempt attempt;
    private Code statusCode;
    private String resultAttempt;
    private int cnt;

    public AttemptResoult(Code code, String resultAttempt, int cnt, Attempt attempt) {
        this.statusCode = code;
        this.resultAttempt = resultAttempt;
        this.cnt = cnt;
        this.attempt = attempt;
    }
    public Attempt getAttempt(){return attempt;}

    public Code getStatusCode() {
        return statusCode;
    }

    public String getAttemptResoult() {
        return resultAttempt;
    }

    public int getCnt(){return cnt;}
}