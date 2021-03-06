package Lista1.Lista1Questao4;

import java.util.ArrayList;

public class APIWeb {
	
	private ArrayList<Server> servers;
	boolean licenseToKill = false;
	
	public APIWeb() {
		servers = new ArrayList<Server>();
	}
	
	public void addServer(Server server) {
		servers.add(server);
	}
	
	public String request(String serversName) {
		String response = "";
		for (Server server : servers) {
			if(server.getServerName().equals(serversName)) {
				response = server.getServerName();
			}
		}
		return response;
	}
	
	
	
	public String reliableRequest(int timeout) {
		Channel channel = new Channel(servers.size());
		ArrayList<Thread> threads = createThreads(channel);
		String msg = channel.takeMessage(timeout);
		System.out.println("Fisrt Server: " + msg);
		cancelThreads(threads);
		return msg;
		
	}

	private void cancelThreads(ArrayList<Thread> threads) {
		for (Thread thread : threads) {
			thread.interrupt();
		}
	}

	private ArrayList<Thread> createThreads(Channel channel) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (Server server : servers) {
			server.setChannel(channel);
			Thread thread = new Thread(server);
			threads.add(thread);
			thread.start();
		}
		return threads;
	}

	public synchronized void loopReliableRequest() {
		while (true){
			try {
				this.wait(1000);
				if(this.licenseToKill) {
					System.out.println("BOND GIRL, KILL CONFIRMED");
					break;
				}
				String taken = reliableRequest(0);
				System.out.println(taken);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
			
	}

	public boolean isLicenseToKill() {
		return licenseToKill;
	}

	public void setLicenseToKill(boolean licenseToKill) {
		this.licenseToKill = licenseToKill;
	}
	
	
	
}