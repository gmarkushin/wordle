package domain;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import infrastructure.DictSecretWords;

public class Service {

    private Random random = new Random();
    private DictSecretWords dictSecretWords;
    private Attempt attempt;
	private String hiddenWord;
	
    public Service(DictSecretWords dictSecretWords) {
        this.dictSecretWords = dictSecretWords;
    }
    public String getHiddenWord() {
        List<String> words = dictSecretWords.getWords();
        int index = random.nextInt(words.size());
        return words.get(index);
    }
    public char[] checkAttempt(String hiddenWord, String extAtt) {
        int len = extAtt.length();
        char[] result = new char[len];

        for (int i = 0; i < len; i++) {
            char currentChar = extAtt.charAt(i);

            if (currentChar == hiddenWord.charAt(i)) {
                result[i] = currentChar;
            } else if (hiddenWord.indexOf(currentChar) >= 0) {
                result[i] = '!'; 
            } else {
                result[i] = 'X'; 
            }
        }
        return result;
    }
    public boolean isTrueWord(String hiddenWord, String extAtt) {
        return hiddenWord.equals(extAtt);
    }
		
}