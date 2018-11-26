package Lista1Questao2DOT3;

public class APIWeb implements Runnable {
	
	private Channel channel;
	
	public APIWeb(Channel channel) {
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
				String msg = this.channel.reliableRequest();
				System.out.println("Fisrt Server: " + msg);
				this.channel.notifyAll();
			}
		}
		
	}
}
