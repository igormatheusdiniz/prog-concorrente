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
			int number = new Random().nextInt(1000);
			this.channel.putNumber(number);
		}
	}

}
