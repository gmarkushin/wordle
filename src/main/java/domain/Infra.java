package domain;

import java.util.List;

public interface Infra {
    default List<String> getWords(String path) {
        return null;
    };
    String getHiddenWord();

    boolean wordExists(String word);
}
