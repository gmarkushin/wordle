package main.domain;
import java.util.List;
import java.util.Random;
public class HiddenWord{
	public String getHiddenWord(List<String> words){
		Random rand = new Random();
		int col = words.size();
		int i = rand.nextInt(col);
		return words.get(i);
	}
}