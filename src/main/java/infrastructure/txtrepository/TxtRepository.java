package infrastructure.txtrepository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class TxtRepository {
    public List<String> dictSecretWords(String path){
        try {
            // Убираем префикс classpath: если он есть
            String resourcePath = path.replace("classpath:", "");
            InputStream inputStream = new ClassPathResource(resourcePath).getInputStream();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                List<String> words = new ArrayList<>();
                String str;
                while((str = reader.readLine()) != null){
                    words.add(str);
                }
                return words;
            }
        }catch (Exception e){
            e.printStackTrace();
            return List.of();
        }
    }
}