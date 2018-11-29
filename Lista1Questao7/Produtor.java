package Lista1Questao7;

import java.nio.charset.Charset;
import java.util.Random;

public class Produtor implements Runnable {

	FilterChannel filterChannel;

	public Produtor(FilterChannel filterChannel) {
		this.filterChannel = filterChannel;
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
		while (true) {
			synchronized (this.filterChannel) {
				while (!this.filterChannel.isEmpty()) {
					try {

						this.filterChannel.wait();
					} catch (InterruptedException e) {
					}
				}

				String generatedString = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 6);

				this.filterChannel.putString(generatedString);
				System.err.println("String produced: " + generatedString);

				this.filterChannel.notifyAll();
			}
		}

	}

}
