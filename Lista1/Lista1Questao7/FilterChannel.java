package Lista1.Lista1Questao7;

import java.util.ArrayList;
import java.util.List;

public class FilterChannel {

	private List<String> messages;

	public FilterChannel() {
		this.messages = new ArrayList<>();

	}

	public synchronized void putString(String message) {

		while (!this.isEmpty()) {
			try {

				this.wait();
			} catch (InterruptedException e) {
			}
		}

		String generatedString = message;
		this.messages.add(message);
		System.err.println("String produced: " + generatedString);
		this.notifyAll();
	}

	public boolean isEmpty() {
		return this.messages.size() == 0;
	}

	public synchronized String takeMessage() {

		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		
		String currentMessage = this.messages.get(0);
		this.messages.remove(0);
		this.notifyAll();
		return currentMessage;
	}

}
