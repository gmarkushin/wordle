package api;

import domain.Game;
import domain.Service;
import domain.Attempt;
import domain.AttemptResoult;
import domain.Code;
import domain.Game;


public class Api {
	Service service;
	Receiver receiver;
	Sender sender;
	public static boolean stopped = false;
	private final String YES = "yes";
	private final String POINT_SPACE = ". ";
	private final String DUOPOINT_SPACE = ": ";
	
	public Api(Service service, Receiver receiver, Sender sender){
		this.service = service;
		this.receiver = receiver;
		this.sender = sender;
		oneCycleGame();
	}
	public void oneCycleGame() {
		sender.send(Message.HELLO);
		if (receiver.getTxt().equals(YES)) {
			do {
				stopped = false;
                Game game = new Game(service.getHiddenWord());
				//hiddenWord = service.getHiddenWord();
				while (service.contin(game)) {
					sender.sendt(game.getCnt());
					sender.sendt(POINT_SPACE);
                    Attempt attempt = new Attempt(receiver.getTxt());
                    AttemptResoult attemptResoult = service.attemptResoult(game.getHiddenWord(), attempt, game);
					if(attemptResoult.getStatusCode().equals(Code.ERR_ATTEMPT)){
						sender.send(Message.ERRATTEMPT);
                        System.out.println(attempt.getAttempt());
						//service.cntAttempt(Code.ERR_ATTEMPT);
						continue;
					}else if(attemptResoult.getStatusCode().equals(Code.NO_EX_WORD)){
						sender.send(Message.NO_EX_WORD);
						//service.cntAttempt(Code.NO_EX_WORD);
						continue;
					}else if(attemptResoult.getStatusCode().equals(Code.WIN_ATTEMPT)){
						sender.send(Message.WIN);
						//service.cntAttempt(Code.WIN_ATTEMPT);
						//service.addWordsAttempts(attempt, attemptResoult);
						break;
					}else if(attemptResoult.getStatusCode().equals(Code.COR_ATTEMPT)){
						sender.send(attemptResoult.getAttemptResoult());
						//service.cntAttempt(Code.COR_ATTEMPT);
						//service.addWordsAttempts(attempt, attemptResoult);
					}
				}
                sender.sendt(Message.LOSE);
                sender.sendt(DUOPOINT_SPACE);
                sender.send(game.getHiddenWord());
                //service.cntAttempt(Code.valueOf("END_GAME"));
				sender.send(Message.START);
				if (!receiver.getTxt().equals(YES)) {
					stopped = true;
				}
			} while (!stopped);
		}
	}
}