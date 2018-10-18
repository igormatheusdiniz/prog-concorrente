package Lista1Questao2;

import java.util.ArrayList;
import java.util.Iterator;

public class DNSlike {
	
	static ArrayList<ServerHTTP> servers = new ArrayList<ServerHTTP>();
	
	public static ServerHTTP registerSite(String URL) {
		ServerHTTP newServer = new ServerHTTP(URL);
		servers.add(newServer);
		return newServer;
	}
	
	public ArrayList<ServerHTTP> listSevers() {
		return this.servers;
	}
	
	public static ServerHTTP get(String URL){
		ServerHTTP respServer = null;
		for (ServerHTTP serverHTTP : servers) {
			if(serverHTTP.URL == URL) {
				respServer = serverHTTP;
				break;
			}
		}
		return respServer;
	}
}
