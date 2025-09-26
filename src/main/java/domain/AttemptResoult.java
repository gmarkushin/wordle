package domain;

import domain.Code;

public class AttemptResoult {
    Code statusCode;
    String resultAttempt;
    int cnt;

    public AttemptResoult(Code code, String attempt, int cnt) {
        this.statusCode = code;
        this.resultAttempt = attempt;
        this.cnt = cnt;
    }

    public Code getStatusCode() {
        return statusCode;
    }

    public String getAttemptResoult() {
        return resultAttempt;
    }
}