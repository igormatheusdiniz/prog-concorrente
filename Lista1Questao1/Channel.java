package Lista1Questao1;

import java.util.ArrayList;
import java.util.List;

public class Channel {

	private int capacidadeMax;
	private List<String> messages;
	private int capacidadeAtual;

	public Channel(int capacidade) {
		this.capacidadeMax = capacidade;
		this.capacidadeAtual = 0;
		this.messages = new ArrayList<>();
	}

	public synchronized void putMessage(String message) {
		while (this.isFull()) {
			try {

				this.wait();
			} catch (InterruptedException e) {
			}
		}
		String receivedMessage = message;

		if (messages.size() + 1 < this.capacidadeMax) {
			this.capacidadeAtual = messages.size() + 1;
			this.messages.add(message);
			System.err.println("message produced: " + receivedMessage);
		} else {
			throw new IllegalArgumentException("Atingiu a capacidade Maxima, chegou a hora de consumir");
		}
		this.notifyAll();
	}

	public boolean isEmpty() {
		return this.messages.size() == 0;
	}

	public boolean isFull() {
		return this.capacidadeAtual == this.capacidadeMax;
	}

	public synchronized  void takeMessage() {
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		String taken = messages.get(0);
		this.messages.remove(0);
		System.out.println("Message consumed: " + taken);
		this.notifyAll();
	}

	public synchronized void getMessages() {
		for (int i = 0; i < messages.size(); i++) {
			System.out.println(messages.get(i));

		}
	}

}
