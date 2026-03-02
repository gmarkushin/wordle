import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import infrastructure.Config;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
    }
}