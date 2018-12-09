package Lista1.Lista1Questao7;

public class Consumidor implements Runnable {

	PrintChannel printChannel;

	public Consumidor(PrintChannel printChannel) {
		this.printChannel = printChannel;
	}

	@Override
	public void run() {
		while (true) {
			String takenMessage = this.printChannel.takeMessage();
			System.out.println("Final Message: " + takenMessage);
			
		}

	}

}
