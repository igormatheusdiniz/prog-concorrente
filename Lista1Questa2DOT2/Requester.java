package Lista1Questa2DOT2;

import java.util.Random;

public class Requester implements Runnable {

	private Server server1;
	private Server server2;

	public Requester(Server server1, Server server2) {
		this.server1 = server1;
		this.server2 = server2;
	}

	public void run() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(5));
		} catch (InterruptedException e) {

			server2.reliableRequest(server1);
		}
	}

}
