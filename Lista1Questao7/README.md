# Ideia:

A solução desta questão é um pouco diferente pois existe uma exigência de que a mesma trabalhe com 2 canais, e diferente de outras questões que tinhamos apenas uma thread consumidora, e uma thread produtora, nesta questão além destas duas temos uma terceira thread que ira agir tanto quanto como consumidora quanto como produtora.

A principal ideia é uma thread produtora gera string aleatorias e as coloca no primeiro canal para serem "analisadas" e filtradas. A thread produtora-consumidora num primeiro momento, com posse dessa mensagem depositada haje como consumidora, e processa essa informação analisando se a mesma é composta apenas por caracteres ALFA, se isto ocorrer a mesma thread trabalhara como "produtora" depositando essa informação em um segundo canal. Por fim a thread exclusivamente consumidora irá processar essa mensagem recebida e printá-la.

# Funcionamento:

## 1- O main cria e "starta" as threads
 
Para este problema, não é requerido que o main faça outra coisa além de criar e startar as threads. Teremos uma Thread consumidora e uma produtora criada além de uma thread que terá os dois comportamentos dependendo do momento, juntamente da instancia do canal que será passado para o construtor de todas as instancias, garantido que todas estejam se comunicando atráves do mesmo canal.
 
## 2 - A Thread produtora solicita vários inserções de mensagem no canal.

A thread implementa a interface Runnable sobreescrevendo portanto o método Run() e dentro deste metódo há uma chamada continua para inserção dentro do canal, atráves do método putString();

```Java
	@Override
	public void run() {
		while (true) {
			String message = this.generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 6);
			this.filterChannel.putString(message);}
	}
```

>  O método auxiliar generateRandomChars() foi criado com o intuito de gerar as strings aleatorias utilizando numeros e letras

```Java
	public String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}

		return sb.toString();
	}
```

## 3- A Thread prodCons solicita o consumo do conteudo disponível no canal analisa-o e em seguida deposita-o no segundo canal.

Aṕos receber um notify do canal de que "está na hora de consumir", faz a chamada para consumir a informação atráves do método takeMessage() que por sua vez encontra-se dentro de um metodo run() sobreescrito pelo fato da classe implementar a interface Runnable. Se a informação for composta de apenas alfa deposita no segundo canal

```Java
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
```
> Importante notar que o metodo isAlpha() é um metodo auxiliar criado que utiliza REGEX para analisar se uma determiada STRING é composta apenas de caracteres alfa.

```Java
	public boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}
```


## FilterChannel

O canal serve de meio de comunicação, possui uma estrtutura de armazenamento de informçaões, nesse caso as mensgens geradas pela thread produtora. Atráves de metodos putString() e takeMessage(), que são sincronizados garantindo a concorrencia, podemos ter o controle do conteudo que tem nessa etrutura. Além disso tais métodos são responsáveis por notificarem tanto a produtora quando o canal estiver pronto para receber informações novas, quanto a consProd quando canal possui informação, para que a mesma possa analisa a informação e colocar ou não no proximo canal. 

```Java
public synchronized void putString(String message) {

		while (!this.isEmpty()) {
			try {

				this.wait();
			} catch (InterruptedException e) {
			}
		}

		String generatedString = message;
		this.messages.add(message);
		System.err.println("String produced: " + generatedString);
		this.notifyAll();
	}

	public boolean isEmpty() {
		return this.messages.size() == 0;
	}
	public synchronized String takeMessage() {

		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		
		String currentMessage = this.messages.get(0);
		this.messages.remove(0);
		this.notifyAll();
		return currentMessage;
	}
 
  ```
  
  ## PrintChannel

Este canal é mais simples do que o anterior e serve de meio de comunicação, possui uma estrtutura de armazenamento de informçaões, nesse caso as mensgens repassdadas pela thread consProd. Atráves de metodos putString() e takeMessage(), que são sincronizados garantindo a concorrencia, podemos ter o controle do conteudo que tem nessa etrutura. Além disso tais métodos são responsáveis por notificarem tanto a produtora quando o canal estiver pronto para receber informações novas, quanto a produtora quando canal possui informação, para que a mesma possa ser printada na tela como mensagem final.

```Java
public synchronized void putString(String message) {
		while (!this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		String takenMessage = message;
		this.messages.add(takenMessage);
		System.err.println("Message filtered: " + takenMessage);
		this.notifyAll();
	}
	public synchronized String takeMessage() {

		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}

		String currentMessage = this.messages.get(0);
		this.messages.remove(0);
		this.notifyAll();
		return currentMessage;
	}
 
  ```
