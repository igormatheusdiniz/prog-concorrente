# Ideia:

A questão pode ser solucionada utilizando uma estrutura de canal similar a primeira questão. O canal é utilizado para abstrair a troca de informações entre uma thread produtora que insere no mesmo os numeros aleatorios e uma consumidora que irá consumir os dados nesta estrutura de armazenamento, printado-os quando os mesmos forem par. 

# Funcionamento:

## 1- O main cria e "starta" as threads
 
Para este problema, não é requerido que o main faça outra coisa além de criar e startar as threads. Teremos uma Thread consumidora e uma produtora criada, juntamente da instancia do canal que será passado para o construtor tanto do produtor quanto do consumidor, garantido que ambas estão se comunicando atráves do mesmo canal.
 
## 2 - A Thread produtora solicita vários inserts no canal.

A thread implementa a interface Runnable sobreescrevendo portanto o método Run() e dentro deste metódo há uma chamada continua para inserção dentro do canal, atráves do método putNumber();

```Java
	@Override
	public void run() {
		while (true) {
			int number = new Random().nextInt(1000);
			this.channel.putNumber(number);
		}
	}
```
## 3- A Thread consumidora solicita o consumo do conteudo disponível no canal.

Aṕos receber um notify do canal de que "está na hora de consumir", faz a chamada para consumir a informação atráves do método takeMessage() que por sua vez encontra-se dentro de um metodo run() sobreescrito pelo fato da classe implementar a interface Runnable.

```Java
	@Override
	public void run() {
		while (true) {
			this.channel.takeMessage();
			}
		
	}
```

## Canal

O canal serve de meio de comunicação, possui uma estrtutura de armazenamento de informçaões, nesse caso os números gerados pela thread produtora. Atráves de metodos putNumber() e takeMessage() que são sincronizados garantindo a concorrencia, podemos ter o controle do conteudo que tem nessa etrutura. Além disso tais métodos são responsáveis por notificarem tanto a produtora quando o canal estiver pronto para receber informações novas, quanto a consumidora quando canal possui informação. 

```Java
public synchronized void putNumber(int number) {
		while (!this.isEmpty()) {
			try {

				this.wait();
			} catch (InterruptedException e) {
			}
		}

		int randomNumber = number;
		this.numbers.add(randomNumber);
		System.err.println("Number produced: " + randomNumber);
		this.notifyAll();
	}

	public synchronized void takeMessage() {
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		int takenNumber = this.numbers.get(0);
		this.numbers.remove(0);
		if ((takenNumber % 2) == 0) {
			System.out.println("Even value: " + takenNumber);
		}
		this.notifyAll();
	}
  
  ```
