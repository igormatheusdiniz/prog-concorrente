package Lista1Questao6;

public class Consumidora implements Runnable {
	
	private Channel channel;
	
	public Consumidora(Channel channel) {
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
				int takenNumber = this.channel.takeMessage();
				if ((takenNumber % 2) == 0) {
					System.out.println("Even value: " + takenNumber);
				}
				this.channel.notifyAll();
			}
		}
	}
	
}
