package Lista1Questa2DOT2;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main2 {

	public static void main(String[] args) {

		final Server server1 = new Server("mirror1.com");
		final Server server2 = new Server("mirror2.br");
		//final Server server3 = new Server("mirror3.edu");
		new Thread(new Requester(server1, server2)).start();
		//new Thread(new Requester(server2)).start(); 
		// new Thread(new Requester(server2, server1, server3)).start();
		// new Thread(new Requester(server3 ,server2, server1)).start();
	}

	static class Requester implements Runnable {
		private Server server1;
		private Server server2;
		private APIRequester api = new APIRequester();
		// private Server server3;

		public Requester(Server server1, Server server2) {
			this.server1 = server1;
			this.server2 = server2; 
			//this.server2 = server2;
			// this.server3 = server3;
		}

		public void run() {
			Random random = new Random();
			for (int counter = 0; counter <= 10; counter++) {
				try {
					Thread.sleep(random.nextInt(5));
				} catch (InterruptedException e) {
				}
				api.reliableRequest(server1, server2);
				//api.reliableRequest(server2);
			}
		}
	}

	static class Server {
		private final String serverName;

		public Server(String name) {
			this.serverName = name;
		}

		public String getName() {
			return this.serverName;
		}

	}

	static class APIRequester {
		
		Server currentServer;
		private final Lock lock = new ReentrantLock();

		
		public APIRequester() {
			// TODO Auto-generated constructor stub
		}
		
		public boolean request(Server serverName1, Server serverName2) {
			Boolean server1 = false;
			Boolean server2 = false;
			// Boolean server3 = false;

			try {
				// tryLock: Acquires the lock only if it is free at the time of invocation.
				server1 = lock.tryLock();
				server2 = lock.tryLock();
				// server3 = serverName.lock.tryLock();
			} finally {
				if (!(server1 && server2)) {
					if (server1) {
						currentServer = serverName1;
						// unlock: Releases the lock.
						lock.unlock();
					}
					if (server2) {
						currentServer = serverName2;
						lock.unlock();
					}
					// if(server3) {
					// serverName2.lock.unlock();
					// }
				}
			}
			//return server1;
			return server1 && server2;
		}

		public void reliableRequest(Server server1, Server server2) {
			// Check if Lock is already exist?
			if (request(server1, server2)) {
				try {
					System.out.println(currentServer.getName());
				} finally {
					lock.unlock();
				}
			} 
		}
	}
}
