package Lista1Questao7;

public class ConsProd implements Runnable{
	
	FilterChannel filterChannel;
	PrintChannel printChannel;
	
	public ConsProd(FilterChannel filterChannel, PrintChannel printChannel) {
		this.filterChannel = filterChannel;
		this.printChannel = printChannel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
