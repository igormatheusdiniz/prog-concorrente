Nesta quest�o temos um t�pico caso Produtor/Consumidor, al�m disto adotamos um canal em comum como meio de comunica��o entre os dois lados. Desta forma temos:

- Algu�m que produzir� as mensagens e as colocar� neste canal em comum, e enquanto o mesmo n�o estiver cheio poder� produzir mensagens e deposita-l�s dentro deste canal;

A seguir temos o m�todo que chama a fun��o de inser��o do Channel, onde ocorre as verifica��es necess�rias para que ocorram a inser��o

    public void run() {
    	while(true) {
    	String message = this.generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 6);
    	this.channel.putMessage(message);
    	}
    }
    

- Algu�m que consumir� as mensagens deste canal, quando receber notifica��o de que ja possui informa��o dentro do mesmo

A seguir temos o m�todo que chama a fun��o de recuperar uma mensagem do channel, onde ocorre as verifica��es necess�rias para que ocorram a recupera��o

	public void run() {
		while (true) {
			this.channel.takeMessage();
		}
	}

- E o pr�prio canal de intermedia��o, onde possui os m�todos putMessage(), e getMessage(). Ambos necessitam de sincronismo com a finalidade de evitar possiveis deadlock. 


	public synchronized void putMessage(String message) {
		while (this.isFull()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		String receivedMessage = message;
		if (messages.size() + 1 < this.capacidadeMax) {
			this.capacidadeAtual = messages.size() + 1;
			this.messages.add(message);
			System.err.println("message produced: " + receivedMessage);
		} else {
			throw new IllegalArgumentException("Atingiu a capacidade Maxima, chegou a hora de consumir");
		}
		this.notifyAll();
	}
	
	
		public synchronized  void takeMessage() {
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		String taken = messages.get(0);
		this.messages.remove(0);
		System.out.println("Message consumed: " + taken);
		this.notifyAll();
	}
	
- Optou-se por criar um Canal a parte uma vez que torna o c�digo mais elegante, e re-us�vel, sem contar que atende a proposta da quest�o.

> Importante !! Nesta quest�o temos um detalhe importante, no que diz respeito a capacidade m�xima da estrutura que armazena as mensagens produzidas. Caso ocorra um estouro do limite estipulado na cria��o do canal , uma exce��o � lan�ada. Para fazer tal verifica��o, nos trabalhamos dentro da fun��o putMessage() em Channel sinalizando que enquanto a estrtutura estiver cheia a thread deve dar um wait.

> Importante 2 !! Optou-se por utilizar um Arraylist como estrutura de armazenamento, uma vez que � de f�cil manipula��o. E de f�cil acesso a elementos indexados, por exemplo "ler a primeira mensagem recebida" equivale a pegar o elemento no indice 0, e como ArrayList � uma estrutura din�mica, logo em seguinda a cabe�a passa a ser o pr�ximo elemento.