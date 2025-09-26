package domain;

public class Service {
	private final char LET_TRUE = '!';
	private final char LET_FALSE = 'X';
	private final String NO_CORR_ATT = "Некорректная попытка";
	private final String WIN_ATT = "Победа!";
	private final int MAXATTEMPTS = 6;
	private final int DEFAULT_CNT = 1;
	private final String NO_EXISTS = "Неизвестное слово";
    private Infra dict;

    public Service(Infra dict){
        this.dict = dict;
    }

	public boolean contin(Game game){
		return game.getCnt()<=MAXATTEMPTS;
	}

    public String getHiddenWord() {
		return dict.getHiddenWord();
    }

    public char[] checkAttempt(String hiddenWord, Attempt attempt) {
        int len = attempt.getAttempt().length();
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            char currentChar = attempt.getAttempt().charAt(i);
            if (currentChar == hiddenWord.charAt(i)) {
                result[i] = currentChar;
            } else if (hiddenWord.contains(String.valueOf(currentChar))) {
                result[i] = LET_TRUE;
            } else {
                result[i] = LET_FALSE;
            }
        }
        return result;
    }

    public boolean isTrueWord(String hiddenWord, String extAtt) {
        return hiddenWord.equals(extAtt);
    }

    public AttemptResoult attemptResoult(String hiddenWord, Attempt attempt, Game game) {
        AttemptResoult attemptResoult;
        if (!attempt.validAttempt()) {
            attemptResoult = new AttemptResoult(Code.ERR_ATTEMPT, NO_CORR_ATT,game.getCnt());
        } else if (isTrueWord(hiddenWord, attempt.getAttempt())) {
            attemptResoult = new AttemptResoult(Code.WIN_ATTEMPT, WIN_ATT,game.getCnt());
        } else if(!dict.wordExists(attempt.getAttempt())){
            attemptResoult = new AttemptResoult(Code.NO_EX_WORD, NO_EXISTS,game.getCnt());
		} else {
            attemptResoult = new AttemptResoult(Code.COR_ATTEMPT, new String(checkAttempt(hiddenWord, attempt)),game.getCnt());
            int cnt = game.getCnt();
            game.setCnt(++cnt);
        }
        game.addResList(attemptResoult);
        return attemptResoult;
    }
}