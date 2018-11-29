package Lista1Questao7;

public class ConsProd implements Runnable {

	FilterChannel filterChannel;
	PrintChannel printChannel;

	public ConsProd(FilterChannel filterChannel, PrintChannel printChannel) {
		this.filterChannel = filterChannel;
		this.printChannel = printChannel;
	}
	
	public boolean isAlpha(String name) {     
		return name.matches("[a-zA-Z]+"); 
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this.filterChannel) {
				while (this.filterChannel.isEmpty()) {
					try {
						this.filterChannel.wait();
					} catch (InterruptedException e) {
					}
				}
				String takenMessage = this.filterChannel.takeMessage();
				if (this.isAlpha(takenMessage)) {
					while (true) {
						synchronized (this.printChannel) {
							while (!this.printChannel.isEmpty()) {
								try {

									this.printChannel.wait();
								} catch (InterruptedException e) {
								}
							}
							this.printChannel.putString(takenMessage);
							System.err.println("Message filtered: " + takenMessage);
							this.printChannel.notifyAll();
							break;
						}
					}
				}
				this.filterChannel.notifyAll();
			}
		}
	}

}
