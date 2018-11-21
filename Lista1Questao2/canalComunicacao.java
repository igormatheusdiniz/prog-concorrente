package Lista1Questao2;

public class canalComunicacao {
	
	String response = "none";
	String[] serversURLs = {"mirror1.com", "mirror2.br", "mirror3.edu"};
	
	public canalComunicacao() {
		
	}
	
	public void reliableRequest() throws InterruptedException {
		this.request("mirror1.com");
		this.request("mirror2.br");
		this.request("mirror3.edu");
			
	}
	
	public String request(String serverURL) throws InterruptedException {
		ServerHTTP mirror1 = new ServerHTTP(this, "mirror1.com");
		ServerHTTP mirror2 = new ServerHTTP(this, "mirror2.br");
		ServerHTTP mirror3 = new ServerHTTP(this, "mirror3.edu");
		
		
		
		Thread t1 = new Thread(mirror1, "Thread-produtora1");
		Thread t2 = new Thread(mirror2, "Thread-produtora2");
		Thread t3 = new Thread(mirror3, "Thread-produtora3");
		
		t1.start();
		t2.start();
		t3.start();
		System.out.println("RESPONDE CANAL COM.: "+ this.response);
		
		t1.join();
		t2.join();
		t3.join();
		return this.response;
	}
	
	public boolean isNone() {
		return this.response.equals("none");
	}
	
	public void putName(String nomeServer) {
		this.response = nomeServer;
	}

}
