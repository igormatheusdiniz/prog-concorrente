package Lista1Questao7;

import java.util.ArrayList;
import java.util.List;

public class FilterChannel {
	
	private List<String> messages;
	
	public FilterChannel() {
		this.messages = new ArrayList<>();
	
	}
	
	public void putString(String message) {
		this.messages.add(message);
	}
	
	public boolean isEmpty() {
        return this.messages.size()==0;
    }
	
	public String takeMessage() {
		String currentMessage = this.messages.get(0);
		this.messages.remove(0);
		return currentMessage;
	}
	
	
	
}
