package lista1Questao2DOT1;

import lista1Questao2DOT1.Main.Server;

public class Requester {

	APIWeb API1;
	APIWeb API2;
	APIWeb API3;
	Server server1;
	Server server2;
	Server server3;

	public Requester(APIWeb api1, APIWeb api2, APIWeb api3) {
		this.API1 = api1;
		this.API2 = api2;
		this.API3 = api3;
	}

	public void request(String serverName) throws InterruptedException {
		if (serverName.equals("mirror1.com")) {
			this.server1 = new Server(API1, "mirror1.com");
		} else if (serverName.equals("mirror2.br")) {
			this.server2 = new Server(API2, "mirror2.br");
		} else if (serverName.equals("mirror3.edu")) {
			this.server3 = new Server(API3, "mirror3.edu");
		}

		if (this.server1 != null && this.server2 != null && this.server3 != null) {
			this.createThreads();
		}
	}

	private void createThreads() throws InterruptedException {
		Thread t0 = new Thread(this.server1, "thead-s0");
		Thread t1 = new Thread(this.server2, "thead-s1");
		Thread t2 = new Thread(this.server3, "thead-s2");

		t0.start();
		t1.start();
		t2.start();

		t0.join();
		t1.join();
		t2.join();

	}
}
