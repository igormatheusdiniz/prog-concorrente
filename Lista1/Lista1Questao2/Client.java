package Lista1.Lista1Questao2;

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
		String finalMessage = consumerAPI.reliableRequest();
		System.out.println("A API retornou: " + finalMessage);
		
	}

}
