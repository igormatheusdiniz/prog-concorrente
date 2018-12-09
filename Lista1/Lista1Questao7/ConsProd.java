package Lista1.Lista1Questao7;

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
			String takenMessage = this.filterChannel.takeMessage();
			if (this.isAlpha(takenMessage)) {
				while (true) {
					//System.err.println("Filtred Message: " + takenMessage);
					this.printChannel.putString(takenMessage);
					break;
				}
			}
		}
	}

}
