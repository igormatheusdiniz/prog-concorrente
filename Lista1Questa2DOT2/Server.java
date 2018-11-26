package Lista1Questa2DOT2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Server {

	private final String server_name;

	private final Lock lock = new ReentrantLock();

	public Server(String server_name) {
		this.server_name = server_name;
	}

	public String getName() {
		return this.server_name;
	}

	public boolean request(Server server_name) {
		Boolean server1 = false;
		Boolean server2 = false;

		try {
			server1 = lock.tryLock();
			server2 = lock.tryLock();

		} finally {
			if (!(server1 && server2)) {
				if (server1) {
					lock.unlock();
				}

				if (server2) {
					server_name.lock.unlock();
				}
			}
		}

		return server1 && server2;

	}

	public void reliableRequest(Server serverName) {
		// Check if Lock is already exist?
		if (request(serverName)) {
			try {
				System.out.println(serverName.getName());
			} finally {
				lock.unlock();
				serverName.lock.unlock();
			}
		} else {
			System.out.format("\tLock Situation ==> I'm %s: talking to %s, but it seems"
					+ " we are already talking. Conflicting. %n", server_name, serverName.getName());
		}
	}

}
