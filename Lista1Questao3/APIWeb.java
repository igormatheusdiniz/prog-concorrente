package Lista1Questao3;

import java.util.ArrayList;


public class APIWeb {
	
	private Channel channel;
	private ArrayList<Server> servers;
	
	public APIWeb() {
		this.channel = new Channel();
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
			server.setChannel(channel);
		}
		
		synchronized (this.channel) {
			while (this.channel.isEmpty()) {
				try {
					this.channel.wait(2000);
				} catch (InterruptedException e) {
					System.out.println("TIMEOUTED");
					e.printStackTrace();
				}
			}
			String msg = this.channel.getMessage();
			System.out.println("Fisrt Server: " + msg);
			this.channel.notifyAll();
			return msg;
		}
	}
	
}