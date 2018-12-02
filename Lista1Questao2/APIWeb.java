package Lista1Questao2;

import java.util.ArrayList;


public class APIWeb {
	
	private Channel channel;
	private ArrayList<Server> servers;
	
	public APIWeb() {
		this.channel = new Channel(3);
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
		for (Server server : servers) {
			server.setChannel(this.channel);
			Thread thread = new Thread(server);
			thread.start();
		}
		String msg = this.channel.takeMessage();
		System.out.println("Fisrt Server: " + msg);
		return msg;
		
	}

	private void cancelServers() {

		
	}
	
}