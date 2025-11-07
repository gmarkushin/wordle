package api.apiLogic;

import api.console.ConsoleWork;
import api.env.Message;
import domain.model.game.Game;
import domain.model.attempt.Attempt;
import domain.model.attempt.AttemptResoult;
import domain.service.Code;
import domain.service.ServiceGame;
import domain.service.ServiceAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Api {
	@Autowired
	ServiceGame serviceGame;
	@Autowired
	ConsoleWork consoleWork;
	@Autowired
	ServiceAttempt serviceAttempt;

	public static boolean stopped = false;
	public static final String YES = "да";
	public static final String POINT_SPACE = ". ";
	public static final String DUOPOINT_SPACE = ": ";

	public void oneCycleGame() {
		consoleWork.send(Message.HELLO);
		if (consoleWork.getString().equals(YES)) {
			do {
				stopped = false;
                Game game = new Game(serviceGame.getHiddenWord());
				while (game.contin()) {
					consoleWork.send(toString(game.getCnt()));
					consoleWork.send(POINT_SPACE);
                    Attempt attempt = new Attempt(consoleWork.getString());
					AttemptResoult attemptResoult = serviceAttempt.attemptResoult(attempt, game);
					if(attemptResoult.getStatusCode().equals(Code.ERR_ATTEMPT)){
						consoleWork.send(Message.ERRATTEMPT+"\n");
                        System.out.println(attempt.getAttempt());
					}else if(attemptResoult.getStatusCode().equals(Code.NO_EX_WORD)){
						consoleWork.send(Message.NO_EX_WORD+"\n");
					}else if(attemptResoult.getStatusCode().equals(Code.WIN_ATTEMPT)){
						consoleWork.send(Message.WIN+"\n");
						serviceGame.addResList(attemptResoult,game);
						serviceGame.saveGame(game);
						break;
					}else if(attemptResoult.getStatusCode().equals(Code.COR_ATTEMPT)){
						serviceGame.addResList(attemptResoult,game);
						serviceGame.saveGame(game);
						serviceGame.cntUp(game);
						consoleWork.send(attemptResoult.getAttemptResoult()+"\n");
					}
				}
				consoleWork.send(Message.LOSE);
				consoleWork.send(DUOPOINT_SPACE);
				consoleWork.send(game.getHiddenWord());
                //service.cntAttempt(Code.valueOf("END_GAME"));
				consoleWork.send(Message.START);
				if (!consoleWork.getString().equals(YES)) {
					stopped = true;
				}
			} while (!stopped);
		}
	}

	private String toString(int cnt) {
		return "" + cnt;
	}
}