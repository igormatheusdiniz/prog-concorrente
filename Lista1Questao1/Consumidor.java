package Lista1Questao1;

public class Consumidor implements Runnable {

	private Channel channel;

	public Consumidor(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this.channel) {
				while (this.channel.isEmpty()) {
					try {
						this.channel.wait();
					} catch (InterruptedException e) {
					}
				}
				int taken = this.channel.takeMessage();
				System.err.println("value consumed: " + taken);
				this.channel.notifyAll();
			}
		}
	}

}