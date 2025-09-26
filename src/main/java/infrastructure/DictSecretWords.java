package infrastructure;

import domain.Infra;

import java.util.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class DictSecretWords implements Infra {
	List<String> words;
	private Random random = new Random();
	static final String DICT_SECRET_PATH = "src/main/resources/dict.txt";
	static final String DICT_PATH = "src/main/resources/dictAll.txt";

    public List<String> getWords(String path){
		try (BufferedReader reader = new BufferedReader(new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)))){
			List<String> words = new ArrayList<>();
			String str;
			while((str = reader.readLine()) != null){
				words.add(str);
			}
			return words;
		}catch (Exception e){
            e.printStackTrace();
			return List.of();
		}
	}
	
	public String getHiddenWord() {
        words = getWords(DICT_SECRET_PATH);
        int index = random.nextInt(words.size());
        return words.get(index);
    }
	
	public boolean wordExists(String word){
		return getWords(DICT_PATH).contains(word);
	}
}