package Lista1Questao1;

import java.util.ArrayList;
import java.util.List;

public class Channel implements iChannel {

	private int capacidadeMax;
	private List<Integer> messages;
	private int capacidadeAtual;

	public Channel(int capacidade) {
		this.capacidadeMax = capacidade;
		this.capacidadeAtual = 0;
		this.messages = new ArrayList<>();
	}

	public void putMessage(int message) {
		if (messages.size() + 1 < this.capacidadeMax) {
			this.capacidadeAtual = messages.size() + 1; 
			this.messages.add(message);
		} else {
			throw new IllegalArgumentException("Atingiu a capacidade Maxima, chegou a hora de consumir");
		}
	}
	
	public boolean isEmpty() {
        return this.messages.size()==0;
    }
	
	public boolean isFull() {
		return this.capacidadeAtual==this.capacidadeMax;
	}

	@Override
	public int takeMessage() {
		return this.messages.remove(0);
	}
	
	public void getMessages(){
		for (int i = 0; i < messages.size(); i++) {
			System.out.println(messages.get(i));
			
		}
	}


}
