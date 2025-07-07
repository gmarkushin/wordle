package api;

import java.util.List;
import infrastructure.DictSecretWords;
import domain.Service;
import domain.Attempt;

public class Api {	
	Attempt attempt;
	//DictSecretWords dictSecretWords = new DictSecretWords();
	Service service;
	String hiddenWord;
	Receiver receiver;
	Sender sender;
	Message message;
	public static final int MAXATTEMPTS = 6;
	public static boolean stopped = false;
	
	public Api(Service service, Receiver receiver, Sender sender, Message message){
		this.service = service;
		this.receiver = receiver;
		this.sender = sender;
		this.message = message;
		oneCycleGame();
	}
	public void oneCycleGame() {
		sender.send(Message.HELLO);
		if (receiver.getTxt().equals("да")) {
			do {
				stopped = false;
				hiddenWord = service.getHiddenWord();
				for (int i = 0; i < MAXATTEMPTS; i++) {
					attempt = new Attempt(receiver.getTxt());//так можно получается?
					if (!attempt.validAttempt()) {
						sender.send(Message.ERRATTEMPT);
						continue;
					}
					if (service.isTrueWord(hiddenWord, attempt.attempt)) {
						sender.send(Message.WIN);
						break;
					} else {
						sender.send(service.checkAttempt(hiddenWord, attempt.attempt));
					}
				}
				if (!service.isTrueWord(hiddenWord, attempt.attempt)) {
					sender.send(Message.LOSE + ": " + hiddenWord);
				}
				sender.send(Message.START);
				if (!receiver.getTxt().equals("да")) {
					stopped = true;
				}
			} while (!stopped);
		}
	}
}