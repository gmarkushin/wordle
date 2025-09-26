package api;

import infrastructure.DictSecretWords;
import domain.Service;

public class Config {
	public DictSecretWords getDictSecretWords(){//в конфге так нельзя как я понял, но и как по другому не понял(((
		return new DictSecretWords();
	}
	
	public Service getService(){
		return new Service(getDictSecretWords());
	}
	
	public Sender getSender(){
		return new Sender();
	}
	
	public Receiver getReceiver(){
		return new Receiver();
	}
	
	public Message getMmessage(){
		return new Message();
	}
	
	public Api getApi(){
		return new Api(getService(), getReceiver(), getSender());
	}
}