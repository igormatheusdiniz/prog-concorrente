package lista1Questao2DOT1;

public class APIWeb {
	
	private String serverName;
	
	public APIWeb(String serverName) {
		this.serverName = serverName;
	}

    public synchronized String reliableRequest() {
        return serverName;
    }

}
