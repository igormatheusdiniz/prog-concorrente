package Lista1Questao6;

import java.util.Random;

public class Produtora implements Runnable {

	Channel channel;

	public Produtora(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this.channel) {
				while (!this.channel.isEmpty()) {
					try {

						this.channel.wait();
					} catch (InterruptedException e) {
					}
				}

				int number = new Random().nextInt(1000);
				//System.out.println(number);
				this.channel.putNumber(number);
				System.err.println("Number produced: " + number);

				this.channel.notifyAll();
			}
		}
	}

}
