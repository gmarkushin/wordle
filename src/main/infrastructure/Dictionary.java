package main.infrastructure;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Dictionary {
	static final String DICT_PATH = "dict.txt";
	public List<String> getWords(){
		try (BufferedReader reader = new BufferedReader(new FileReader(DICT_PATH))){
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
}