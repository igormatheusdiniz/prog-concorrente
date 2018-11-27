package Lista1Questao3;

import java.util.ArrayList;
import java.util.List;

public class Channel {

	private List<String> messages;

	public Channel() {
		this.messages = new ArrayList<>();
	}

	public void putMessage(String serverName) {
		messages.add(serverName);
	}
	
	public boolean isEmpty() {
        return this.messages.size()==0;
    }
	
	public String getMessage() {
		return this.messages.get(0);
	}
	
}
