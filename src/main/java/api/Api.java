package api;

import java.util.List;
import infrastructure.DictSecretWords;
import domain.Service;
import domain.Attempt;
import domain.AttemptResoult;
import domain.Code;

public class Api {	
	Attempt attempt;
	Service service;
	String hiddenWord;
	Receiver receiver;
	Sender sender;
	Message message;
	AttemptResoult attemptResoult;
	public static boolean stopped = false;
	private final String YES = "да";
	private final String NO = "нет";
	private final String POINT_SPACE = ". ";
	private final String DUOPOINT_SPACE = ": ";
	
	public Api(Service service, Receiver receiver, Sender sender, Message message){
		this.service = service;
		this.receiver = receiver;
		this.sender = sender;
		this.message = message;
		oneCycleGame();
	}
	public void oneCycleGame() {
		sender.send(Message.HELLO);
		if (receiver.getTxt().equals(YES)) {
			do {
				stopped = false;
				hiddenWord = service.getHiddenWord();
				while (service.contin()) {
					sender.sendt(service.getCnt());
					sender.sendt(POINT_SPACE);
					attempt = new Attempt(receiver.getTxt());
					attemptResoult = service.attemptResoult(hiddenWord, attempt);
					if(attemptResoult.getStatusCode().equals(Code.valueOf("ERR_ATTEMPT"))){
						sender.send(Message.ERRATTEMPT);
						service.cntAttempt(Code.valueOf("ERR_ATTEMPT"));
						continue;
					}else if(attemptResoult.getStatusCode().equals(Code.valueOf("NO_EX_WORD"))){
						sender.send(Message.NO_EX_WORD);
						service.cntAttempt(Code.valueOf("NO_EX_WORD"));
						continue;
					}else if(attemptResoult.getStatusCode().equals(Code.valueOf("WIN_ATTEMPT"))){
						sender.send(Message.WIN);
						service.cntAttempt(Code.valueOf("WIN_ATTEMPT"));
						service.addWordsAttempts(attempt, attemptResoult);
						break;
					}else if(attemptResoult.getStatusCode().equals(Code.valueOf("COR_ATTEMPT"))){
						sender.send(attemptResoult.getAttemptResoult());
						service.cntAttempt(Code.valueOf("COR_ATTEMPT"));
						service.addWordsAttempts(attempt, attemptResoult);
					}
				}
				if (!service.isTrueWord(hiddenWord, attempt.attempt)) {
					sender.sendt(Message.LOSE);
					sender.sendt(DUOPOINT_SPACE);
					sender.send(hiddenWord);
					service.cntAttempt(Code.valueOf("END_GAME"));
				}
				sender.send(Message.START);
				if (!receiver.getTxt().equals(YES)) {
					stopped = true;
				}
			} while (!stopped);
		}
	}
}