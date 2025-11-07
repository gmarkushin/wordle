package domain.model.attempt;

import domain.service.Code;

public class AttemptResoult {
    Attempt attempt;
    Code statusCode;
    String resultAttempt;
    int cnt;

    public AttemptResoult(Code code, String resultAttempt, int cnt, Attempt attempt) {
        this.statusCode = code;
        this.resultAttempt = resultAttempt;
        this.cnt = cnt;
        this.attempt = attempt;
    }

    public Code getStatusCode() {
        return statusCode;
    }

    public String getAttemptResoult() {
        return resultAttempt;
    }
}