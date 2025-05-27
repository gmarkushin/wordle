package main.domain;
public class Checker{
	public char[] checkAttempt(String hiddenWord, String attempt){
		int colSim = attempt.length();
		char[] result = new char[colSim];
		for(int i=0;i<colSim;i++){
			if(attempt.charAt(i)==hiddenWord.charAt(i)){
				result[i]=attempt.charAt(i);
			}
		}
		for(int i=0;i<colSim;i++){
			if(hiddenWord.contains(String.valueOf(attempt.charAt(i)))&&result[i]=='\u0000'){
				result[i]='!';
			}else if(!hiddenWord.contains(String.valueOf(attempt.charAt(i)))){
				result[i] ='X';
			}		
		}
		return result;
	}
    public boolean isTrueWord(String hiddenWord, String attempt) {
        return hiddenWord.equals(attempt);
    }
}