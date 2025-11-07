package api.console;

import java.io.Console;
import api.apiLogic.ApiInterface;
import org.springframework.stereotype.Component;

@Component
public class ConsoleWork implements ApiInterface  {
    Console cons = System.console();

    public String getString(){
        return cons.readLine();
    }

    public void send(String mess){
        System.out.print(mess);
    }

}
