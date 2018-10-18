package Lista1Questao2;

public class Main {

	public static void main(String[] args) {
		DNSlike.registerSite("mirror1.com");
		DNSlike.registerSite("mirror2.br");
		DNSlike.registerSite("mirror3.edu");
		
		ClientSystem clientSystem = new ClientSystem();
		clientSystem.reliableRequest();
		
	}
	
	
}
