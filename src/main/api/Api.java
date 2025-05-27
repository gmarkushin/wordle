package main.api;
import java.util.List;
import main.domain.Service;
public class Api {
	public static final int MAXATTEMPTS = 6;
	// Sender sender;
	// Receiver receiver;
	// Service service;
	String attempt;
	public static final String YES = "да";
	public static final String NO = "нет";
	Sender sender = new Sender();
	Receiver receiver = new Receiver();
	Service service = new Service();
	public void gameProc(){
		sender.send(Message.HELLO);
		while(true){
			String secretWord = service.proxGetHiddenWord(service.words);
			sender.send(Message.START);
			String askStart = receiver.getTxt();
			if(YES.equals(askStart)){
				sender.send(Message.ATTEPMT);
				for(int i=0;i<MAXATTEMPTS;i++){
					receiver.setAttempt();
					attempt = receiver.getAttempt();
					if(service.proxIsTrueWord(secretWord,attempt)){
						sender.send(Message.WIN);
						break;
					}
					if(!service.proxVolidFiveLett(attempt)){
						sender.send(Message.FIVELETTER);
						continue;
					}
					if(!service.proxVolidRusLang(attempt)){
						sender.send(Message.RUSLANG);
						continue;
					}
					sender.send(service.proxCheckAttempt(secretWord,attempt));
					if(i==MAXATTEMPTS){
						sender.send(Message.LOSE);
						sender.send(secretWord);
						}
				}
			}else if (NO.equals(askStart)){
					sender.send(Message.END);
					break;
				}else{
					sender.send(Message.ERRSTART);
					break;
					}
			}
		}
	}