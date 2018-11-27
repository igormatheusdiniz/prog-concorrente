package Lista1Questao2;

public class Client {
	
	public static void main(String[] args) throws InterruptedException {
		
		Channel channel = new Channel();
		APIWeb consumerAPI = new APIWeb(channel); //Consumidor
		Server server1 = new Server("mirro1.com", channel); //Produtor
		Server server2 = new Server("mirror2.br", channel); //Produtos
		Server server3 = new Server("mirror3.edu", channel); //Produtor
		
		Thread t0 = new Thread(server1, "Thread-Produtora1");
		Thread t1 = new Thread(server2, "Thread-Produtora2");
		Thread t2 = new Thread(server3, "Thread-Produtora3");
		Thread t3 = new Thread(consumerAPI, "Thread-Consumidora");
		
		t0.start();
		t1.start();
		t2.start();
		t3.start();		
		
	}

}
