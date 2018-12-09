package Lista1.Lista1Questao1;

public class Consumidor implements Runnable {

	private Channel channel;

	public Consumidor(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void run() {
		while (true) {
			this.channel.takeMessage();
		}
	}
}