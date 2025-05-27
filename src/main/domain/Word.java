package main.domain;
public class Word {
	public String word;
	int maxLett = 5;
	public boolean volidFiveLett(String word){
		return word.length()== maxLett;
	}
	public boolean volidRusLang(String word){
		return word.matches("[а-яё]{5}");
		}
}