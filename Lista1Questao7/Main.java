package Lista1Questao7;

public class Main {
	
	public static void main(String[] args) {
		
		PrintChannel printChannel = new PrintChannel();
		FilterChannel filterChannel = new FilterChannel();
		Produtor produtor = new Produtor(filterChannel);
		ConsProd consProd = new ConsProd(filterChannel, printChannel);
		Consumidor consumidor = new Consumidor(printChannel);
		
		Thread t0 = new Thread(produtor, "Thread-produtora");
		Thread t1 = new Thread(consProd, "Thread-produtora-consumidora");
		Thread t2 = new Thread(consumidor, "Thread-Consumidora");

		t0.start();
		t1.start();
		t2.start();
		System.out.println("Fim da execuçao");
		
	}

}
