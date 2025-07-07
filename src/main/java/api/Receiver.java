package api;

import java.io.Console;

public class Receiver{
	String attempt;
	Console cons = System.console();
	public void setAttempt(){
		this.attempt = cons.readLine();
	}
	public String getAttempt(){
		return attempt;
	}
	public String getTxt(){
		return cons.readLine();
	}
}
