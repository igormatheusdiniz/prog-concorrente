package Lista1Questao4;

import java.util.Random;

public class Client {
		
	public static void main(String[] args) throws InterruptedException {
		
		//SETUP
		APIWeb consumerAPI = new APIWeb(); //Consumidor
		Server server1 = new Server("mirro1.com"); //Produtor
		Server server2 = new Server("mirror2.br"); //Produtos
		Server server3 = new Server("mirror3.edu"); //Produtor
		consumerAPI.addServer(server1);
		consumerAPI.addServer(server2);
		consumerAPI.addServer(server3);		
		
		//CHAMAR API
		
		Thread thread = new Thread(new OutroFluxo(consumerAPI));
		thread.start();
		consumerAPI.loopReliableRequest();
		
	}
	
}

class OutroFluxo implements Runnable{

	APIWeb object;
	
	public OutroFluxo(APIWeb object) {
		super();
		this.object = object;
	}

	@Override
	public void run() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(10000));
		} catch (InterruptedException e1) {
			System.out.println(e1.getMessage());
		}
		// 007, Licence to Kill
		synchronized (object){
			if(object != null) {
				System.out.println("007, KILL");
				object.setLicenseToKill(true);
				object.notifyAll();
			}			
		}
	}
	
}