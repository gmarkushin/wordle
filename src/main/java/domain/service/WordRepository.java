package domain.service;

public interface WordRepository {
    public boolean wordExists(String word);
    public String getRandomWord();
}
