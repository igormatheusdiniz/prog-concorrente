package Lista1Questao2DOT3;

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
	
	public String takeFisrtAnswer() {
		return this.messages.remove(0);
	}
	
}
