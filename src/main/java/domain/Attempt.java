package domain;

public class Attempt {
    
    public String attempt;
	public static final int MAX_LETTERS = 5;
	
	public boolean validAttempt(){
		return attempt != null && attempt.length() == MAX_LETTERS && attempt.matches("[а-яё]{5}");
	}

	public Attempt(String extAtt){
		this.attempt = extAtt;
	}
	
}