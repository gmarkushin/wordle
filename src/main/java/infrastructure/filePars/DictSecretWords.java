package infrastructure.filePars;

import domain.model.game.Game;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import domain.service.Repository;
import org.springframework.stereotype.Component;

@Component
public class DictSecretWords implements Repository {
	List<String> words;
	private Random random = new Random();
	private final Map<UUID, Game> gameHistory = new HashMap<>();
	static final String DICT_SECRET_PATH = "src/main/resources/dict.txt";
	static final String DICT_PATH = "src/main/resources/dictAll.txt";

    private List<String> DictSecretWords(String path){
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
        words = DictSecretWords(DICT_SECRET_PATH);
        int index = random.nextInt(words.size());
        return words.get(index);
    }
	
	public boolean wordExists(String word){
		return DictSecretWords(DICT_PATH).contains(word);
	}

	public void saveGame(Game game) {
		gameHistory.put(game.getUid(), game);
	}
}