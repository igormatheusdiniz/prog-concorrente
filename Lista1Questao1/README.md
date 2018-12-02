Nesta questão temos um típico caso Produtor/Consumidor, além disto adotamos um canal em comum como meio de comunicação entre os dois lados. Desta forma temos:

- Alguém que produzirá as mensagens e as colocará neste canal em comum, e enquanto o mesmo não estiver cheio poderá produzir mensagens e deposita-lás dentro deste canal;

A seguir temos o método que chama a função de inserção do Channel, onde ocorre as verificações necessárias para que ocorram a inserção

    public void run() {
    	while(true) {
    	String message = this.generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 6);
    	this.channel.putMessage(message);
    	}
    }
    

- Alguém que consumirá as mensagens deste canal, quando receber notificação de que ja possui informação dentro do mesmo

A seguir temos o método que chama a função de recuperar uma mensagem do channel, onde ocorre as verificações necessárias para que ocorram a recuperação

	public void run() {
		while (true) {
			this.channel.takeMessage();
		}
	}

- E o próprio canal de intermediação, onde possui os métodos putMessage(), e getMessage(). Ambos necessitam de sincronismo com a finalidade de evitar possiveis deadlock. 


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
	
- Optou-se por criar um Canal a parte uma vez que torna o código mais elegante, e re-usável, sem contar que atende a proposta da questão.

> Importante !! Nesta questão temos um detalhe importante, no que diz respeito a capacidade máxima da estrutura que armazena as mensagens produzidas. Caso ocorra um estouro do limite estipulado na criação do canal , uma exceção é lançada. Para fazer tal verificação, nos trabalhamos dentro da função putMessage() em Channel sinalizando que enquanto a estrtutura estiver cheia a thread deve dar um wait.

> Importante 2 !! Optou-se por utilizar um Arraylist como estrutura de armazenamento, uma vez que é de fácil manipulação. E de fácil acesso a elementos indexados, por exemplo "ler a primeira mensagem recebida" equivale a pegar o elemento no indice 0, e como ArrayList é uma estrutura dinâmica, logo em seguinda a cabeça passa a ser o próximo elemento.