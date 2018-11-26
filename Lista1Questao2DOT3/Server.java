package Lista1Questao2DOT3;

public class Server implements Runnable{
	
	private String serverName;
	private Channel channel;
	
	public Server(String serverName, Channel channel) {
		this.serverName = serverName;
		this.channel = channel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
