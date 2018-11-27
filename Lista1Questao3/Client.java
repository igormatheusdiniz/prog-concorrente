package Lista1Questao3;

public class Client {
	
	public static void main(String[] args) throws InterruptedException {
		
		//SETUP
		APIWeb consumerAPI = new APIWeb(); //Consumidor
		Server server1 = new Server("mirro1.com"); //Produtor
		Server server2 = new Server("mirror2.br"); //Produtos
		Server server3 = new Server("mirror3.edu"); //Produtor
		Thread t0 = new Thread(server1, "Thread-Produtora1");
		Thread t1 = new Thread(server2, "Thread-Produtora2");
		Thread t2 = new Thread(server3, "Thread-Produtora3");
		consumerAPI.addServer(server1);
		consumerAPI.addServer(server2);
		consumerAPI.addServer(server3);		
		
		//CHAMAR API
		t0.start();
		t1.start();
		t2.start();
		consumerAPI.reliableRequest();
		
	}

}
