package Lista1.Lista1Questao2;

import java.util.ArrayList;


public class APIWeb {
	
	private ArrayList<Server> servers;
	
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
	
	
	public String reliableRequest() {
		Channel channel = new Channel(servers.size());
		ArrayList<Thread> threads = createThreads(channel);
		String msg = channel.takeMessage();
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
	
}