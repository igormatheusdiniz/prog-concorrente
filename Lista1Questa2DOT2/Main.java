package Lista1Questa2DOT2;

public class Main {
	
	public static void main(String[] args) {

		final Server server1 = new Server("mirror1.com");
		final Server server2 = new Server("mirror2.br");
		
		new Thread(new Requester(server1, server2)).start();
		new Thread(new Requester(server2, server1)).start();
		
	}
	
}
