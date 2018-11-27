package Lista1Questao2;

import java.util.Random;

public class Server implements Runnable {

	private String serverName;
	private Channel channel;

	public Server(String serverName, Channel channel) {
		this.serverName = serverName;
		this.channel = channel;
	}

	@Override
	public void run() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(2000));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		synchronized (this.channel) {
				while (!this.channel.isEmpty()) {
					try {

						this.channel.wait();
					} catch (InterruptedException e) {
					}
				}
				//int message = new Random().nextInt(11);
				this.channel.putServer(this.serverName);
				System.out.println("Server produced: " + this.serverName);

				this.channel.notifyAll();
			}
		}
	

}
