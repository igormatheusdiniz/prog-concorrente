package Lista1Questao1;

import java.util.Random;

public class Produtor implements Runnable {
	
	private Channel channel;

    public Produtor(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (this.channel) {
                while (!this.channel.isEmpty()) {
                    try {

                        this.channel.wait();
                    } catch (InterruptedException e) { }
                }
                while(!this.channel.isFull()) {
                int message = new Random().nextInt(11);
                this.channel.putMessage(message);
                System.err.println("message produced: " + message);
                }
                this.channel.notifyAll();
            }
        }
    }

}