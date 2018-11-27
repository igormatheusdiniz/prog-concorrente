package Lista1Questao2;

import java.util.ArrayList;
import java.util.List;

public class Channel {

	private List<String> messages;

	public Channel() {
		this.messages = new ArrayList<>();
	}

	public void putServer(String serverName) {
		messages.add(serverName);
	}
	
	public boolean isEmpty() {
        return this.messages.size()==0;
    }
	
	public String reliableRequest() {
		return this.messages.get(0);
	}
	
}
