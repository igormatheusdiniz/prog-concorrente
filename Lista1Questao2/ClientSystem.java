package Lista1Questao2;

import java.util.concurrent.TimeUnit;

public class ClientSystem implements Runnable{
	
	
	String[] serversURLs = {"mirror1.com", "mirror2.br", "mirror3.edu"};
	
	public String reliableRequest() {
		String response = null;
		
		Runnable task1 = () -> {
			System.out.println(request(serversURLs[0]));
        };
        
		Runnable task2 = () -> {
            System.out.println(request(serversURLs[1]));
        };
        
		Runnable task3 = () -> {
            System.out.println(request(serversURLs[2]));
        };
        
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);
        
        t1.start();
        t2.start();
        t3.start();
        
        
        
		return null;
	}
	
	private String request(String serverURL) {
		ServerHTTP server = DNSlike.get(serverURL);
		return server.getURL();
	}

	@Override
	public void run() {
		reliableRequest();
		
	}
	
}
