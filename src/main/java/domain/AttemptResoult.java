package domain;

public class AttemptResoult {
    Code statusCode;
    String resultAttempt;

    public AttemptResoult(Code code, String attempt) {
        this.statusCode = code;
        this.resultAttempt = attempt;
    }

    public Code getStatusCode() {
        return statusCode;
    }

    public String getAttemptResoult() {
        return resultAttempt;
    }
}