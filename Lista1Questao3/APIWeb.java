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
		server.setChannel(this.channel);
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


		synchronized (this.channel) {
			String msg = "";
			while (this.channel.isEmpty()) {
				try {
					this.channel.wait(2000);
					if(this.channel.isEmpty()) {
						msg = "Timeout!!";
						return msg;
					}else{
						msg = this.channel.getMessage();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.channel.notifyAll();
			return msg;
		}
	}
	
}