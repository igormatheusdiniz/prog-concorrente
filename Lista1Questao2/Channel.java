package Lista1Questao2;

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
				System.out.println(e.getMessage());
			}
		}

		if (messages.size() + 1 <= this.capacidadeMax) {
			this.capacidadeAtual = messages.size() + 1;
			this.messages.add(message);
		} else {
			throw new IllegalArgumentException("Atingiu a capacidade Maxima, hora de consumir");
		}
		this.notifyAll();
	}

	public boolean isEmpty() {
		return this.messages.size() == 0;
	}

	public boolean isFull() {
		return this.capacidadeAtual == this.capacidadeMax;
	}

	public synchronized String takeMessage() {
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		String taken = messages.get(0);
		this.messages.remove(0);
		this.notifyAll();
		return taken;
	}

	public synchronized void getMessages() {
		for (int i = 0; i < messages.size(); i++) {
			System.out.println(messages.get(i));

		}
	}

}
