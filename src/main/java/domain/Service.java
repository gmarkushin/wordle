package domain;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import infrastructure.DictSecretWords;

public class Service {
    private Random random = new Random();
    private DictSecretWords dictSecretWords;
	private int cnt = 1;
	private final char LET_TRUE = '!';
	private final char LET_FALSE = 'X';
	private final String NO_CORR_ATT = "Некорректная попытка";
	private final String WIN_ATT = "Победа!";
	private final int MAXATTEMPTS = 6;
	private final int DEFAULT_CNT = 1;
	private final String NO_EXISTS = "Неизвестное слово";
	private List<String> wordsAttempts = new ArrayList<>();
	
	public void addWordsAttempts(Attempt attempt, AttemptResoult attemptResoult){
		wordsAttempts.add(attempt.getAttempt());
		wordsAttempts.add(attemptResoult.getAttemptResoult());
	}
	
	public boolean contin(){
		return cnt<=MAXATTEMPTS;
	}

    public Service(DictSecretWords dictSecretWords) {
        this.dictSecretWords = dictSecretWords;
    }

    public String getHiddenWord() {
		return dictSecretWords.getHiddenWord();
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

    public AttemptResoult attemptResoult(String hiddenWord, Attempt attempt) {
        if (!attempt.validAttempt()) {
            return new AttemptResoult(Code.valueOf("ERR_ATTEMPT"), NO_CORR_ATT);
        } else if (isTrueWord(hiddenWord, attempt.getAttempt())) {
            return new AttemptResoult(Code.valueOf("WIN_ATTEMPT"), WIN_ATT);
        } else if(!dictSecretWords.wordExists(attempt.getAttempt())){
			return new AttemptResoult(Code.valueOf("NO_EX_WORD"), NO_EXISTS);
		} else {
            return new AttemptResoult(Code.valueOf("COR_ATTEMPT"), new String(checkAttempt(hiddenWord, attempt)));
        }
    }
	
	public void cntAttempt(Code code){
		if(code.equals(Code.valueOf("ERR_ATTEMPT"))||code.equals(Code.valueOf("NO_EX_WORD"))){
			cnt=cnt;
		}else if(code.equals(Code.valueOf("WIN_ATTEMPT"))){
			cnt = DEFAULT_CNT;
		}else if(code.equals(Code.valueOf("COR_ATTEMPT"))){
			cnt++;
		}else{
			cnt = DEFAULT_CNT;
		}
	}
	
	public int getCnt(){
		return cnt;
	}
}