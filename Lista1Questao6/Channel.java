package Lista1Questao6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Channel {

	private List<Integer> numbers;

	public Channel() {
		this.numbers = new ArrayList<>();
	}

	public synchronized void putNumber(int number) {
		while (!this.isEmpty()) {
			try {

				this.wait();
			} catch (InterruptedException e) {
			}
		}

		int randomNumber = number;
		this.numbers.add(randomNumber);
		System.err.println("Number produced: " + randomNumber);
		this.notifyAll();
	}

	public synchronized void takeMessage() {
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		int takenNumber = this.numbers.get(0);
		this.numbers.remove(0);
		if ((takenNumber % 2) == 0) {
			System.out.println("Even value: " + takenNumber);
		}
		this.notifyAll();
	}
	
	public boolean isEmpty() {
		return this.numbers.size() == 0;
	}

		

}
