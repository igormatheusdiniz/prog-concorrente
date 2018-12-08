package Lista1Questao1;

import java.util.Random;

public class Produtor implements Runnable {
	
	private Channel channel;

    public Produtor(Channel channel) {
        this.channel = channel;
    }
    
    public String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}

		return sb.toString();
	}

    @Override
    public void run() {
    	while(true) {
    	String message = this.generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 6);
    	this.channel.putMessage(message);
    	}
    }

}