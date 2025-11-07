import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import api.apiLogic.Api;
import infrastructure.Config;


//измененияcgvfgsg
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        Api api = ctx.getBean(Api.class);
        api.oneCycleGame();
    }
}