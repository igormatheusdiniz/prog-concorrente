package Lista1Questao7;

import java.nio.charset.Charset;
import java.util.Random;

public class Produtor implements Runnable {

	FilterChannel filterChannel;

	public Produtor(FilterChannel filterChannel) {
		this.filterChannel = filterChannel;
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

				byte[] array = new byte[7]; // length is bounded by 7
			    new Random().nextBytes(array);
			    String generatedString = new String(array, Charset.forName("UTF-8"));

				this.filterChannel.putString(generatedString);
				System.err.println("String produced: " + generatedString);

				this.filterChannel.notifyAll();
			}
		}

	}

}
