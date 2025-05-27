package main.domain;
import java.util.List;
import main.infrastructure.Dictionary;
public class Service {
	HiddenWord hiddenWord;
	Checker checker;
	Word wordc;
	Dictionary dictionary;
	public List<String> words;
	
	public HiddenWord hiddenWord(){
		return new HiddenWord();
	}
	public Checker checker(){
		return new Checker();
	}
	public Word word(){
		return new Word();
	}
	
    public Service() {
		this.dictionary = new Dictionary();
		this.words = dictionary.getWords();
        this.hiddenWord = hiddenWord();
        this.checker = checker();
        this.wordc = word();
    }	
	public String proxGetHiddenWord(List<String> words){
		return hiddenWord.getHiddenWord(words);
	}
	public boolean proxVolidFiveLett(String word){
		return wordc.volidFiveLett(word);
	}
	public boolean proxVolidRusLang(String word){
		return wordc.volidRusLang(word);
	}
	public char[] proxCheckAttempt(String hiddenWord, String attempt){
		return checker.checkAttempt(hiddenWord, attempt);
	}
	public boolean proxIsTrueWord(String hiddenWord, String attempt){
		return checker.isTrueWord(hiddenWord,attempt);
	}
}