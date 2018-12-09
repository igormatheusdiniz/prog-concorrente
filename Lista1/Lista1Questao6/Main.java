package Lista1.Lista1Questao6;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		Channel channel = new Channel();
		Produtora produtora = new Produtora(channel);
		Consumidora consumidora = new Consumidora(channel);
		
		Thread t0 = new Thread(produtora, "Thread-produtora");
		Thread t1 = new Thread(consumidora, "Thread-Consumidora");

		t0.start();
		t1.start();
		System.out.println("Fim da execuçao");

	}

}
