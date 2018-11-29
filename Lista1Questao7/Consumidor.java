package Lista1Questao7;

public class Consumidor implements Runnable {

	PrintChannel printChannel;

	public Consumidor(PrintChannel printChannel) {
		this.printChannel = printChannel;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this.printChannel) {
				while (this.printChannel.isEmpty()) {
					try {
						this.printChannel.wait();
					} catch (InterruptedException e) {
					}
				}
				String takenMessage = this.printChannel.takeMessage();
				System.out.println("Final Message: " + takenMessage);
				this.printChannel.notifyAll();
			}
		}

	}

}
