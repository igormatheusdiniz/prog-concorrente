package Lista1Questao3;


import java.util.Random;

public class Server implements Runnable {

	private String serverName;
	private Channel channel;

	public Server(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public void run() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(10000));
		} catch (InterruptedException e1) {
			System.out.println(getServerName() + " KILLED");
			return;
		}
		synchronized (this.channel) {
			while (!this.channel.isEmpty()) {
				try {

					this.channel.wait();
				} catch (InterruptedException e) {
				}
			}
			// int message = new Random().nextInt(11);
			this.channel.putMessage(this.serverName);
			System.out.println("Server produced: " + this.serverName);

			this.channel.notifyAll();
		}
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;		
	}
	
	

}
