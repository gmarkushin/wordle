package infrastructure.txtrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Component
public class WordRepository implements domain.service.WordRepository {

    @Value("${path.dictsecretwordtxt}")
    private String dict_secret_path;
    private List<String> words;
    @Autowired
    private TxtRepository txtRepository;
    private Random random = new Random();

    @Value("${path.dicttxt}")
    private String dict_path;

    @PostConstruct
    public void init() {
        words = txtRepository.dictSecretWords(dict_secret_path);
        System.out.println("Загружено секретных слов: " + words.size());

        if (words.isEmpty()) {
            System.err.println("ВНИМАНИЕ: Словарь секретных слов пуст! Путь: " + dict_secret_path);
        }
    }

    public boolean wordExists(String word){
        return txtRepository.dictSecretWords(dict_path).contains(word);
    }

    public String getRandomWord() {

        System.out.println("woerd.size(): " + words.size());
        int index = random.nextInt(words.size());
        return words.get(index);
    }
}
