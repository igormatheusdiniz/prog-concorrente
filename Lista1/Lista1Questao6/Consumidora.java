package Lista1Questao6;

public class Consumidora implements Runnable {
	
	private Channel channel;
	
	public Consumidora(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public void run() {
		while (true) {
			this.channel.takeMessage();
			}
		
	}
	
}
