package Lista1Questao6;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	
	private List<Integer> numbers;
	
	public Channel() {
		this.numbers = new ArrayList<>();
	}
	
	public void putNumber(int number) {
		this.numbers.add(number);
	}
	
	public boolean isEmpty() {
        return this.numbers.size()==0;
    }
	
	public int takeMessage() {
		int number = this.numbers.get(0);
		this.numbers.remove(0);
		return number;
	}

}
