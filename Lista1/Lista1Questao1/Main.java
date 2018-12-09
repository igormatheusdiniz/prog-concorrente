package Lista1.Lista1Questao1;

public class Main {

	public static void main(String args[]) throws InterruptedException {

		Channel channel = new Channel(8);
		Produtor produtor = new Produtor(channel);
		Consumidor consumidor = new Consumidor(channel);
		
		Thread t0 = new Thread(produtor, "Thread-produtora");
		Thread t1 = new Thread(consumidor, "Thread-Consumidora");

		t0.start();
		t1.start();
		System.out.println("Fim da execuçao");
		
		t0.join();
		t1.join();
		
	}
}
