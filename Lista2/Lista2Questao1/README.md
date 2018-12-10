# Ideia:

A questão pode ser solucionada utilizando a ideia de canal, e em GO tem-se um canal nativo que precisa apenas ser criado e repassado para as threads criadas. O canal trata as questões concorrentes de implementação e é utilizado para abstrair a troca de informações entre a API e os servidores. O servidor dorme por um tempo indeterminado e então retorna algo.
Cada thread criada na API é como uma request HTTP. 

# Funcionamento:

## 1- O cliente chama a função reliable request que está presente dentro da API
 
 Após fazer um setup da API, passando os servidores que devem ser acessados o cliente chama da API utilizando o método reliableRequest().
 
```Java

 		String finalMessage = consumerAPI.reliableRequest();
		System.out.println("A API retornou: " + finalMessage);
```

## 2- A API inicia o canal e as threads, uma para cada request, e espera a primeira retornar.

O método reliable request cria um canal e as threads, onde cada uma seria uma requisição. As threads são configuradas para utilizar o canal criado para troca de informações. 

A execução fica bloqueada no método channel.takeMessage() até que alguma thread retorne alguma informação.

```Java
	public String reliableRequest() {
		Channel channel = new Channel(servers.size());
		ArrayList<Thread> threads = createThreads(channel);
		String msg = channel.takeMessage();
		System.out.println("Fisrt Server: " + msg);
		cancelThreads(threads);
		return msg;
		
	}
```
  

## 3- O servidor cancela as outras requests assim que receber uma resposta e retorna a informação para o cliente.

Aṕos receber uma informação do canal a API então cancela as outras requisições e retorna a mensagem.

Nesse caso sabemos que as threads eventualmente retornarão e não nos preocupamos com tratamento de exceções ou timeout por parte da API. 

## Servidor

O código no servidor é bem simples. Ele dorme por um tempo randômico e então coloca uma mensagem com o nome do servidor no canal. 

```Java
	@Override
	public void run() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(2000));
		} catch (InterruptedException e1) {
			System.out.println(getServerName() + " KILLED");
			return;
		}
		this.channel.putMessage(this.serverName);
		System.out.println("Server produced: " + this.serverName);
		
	}
  
  ```
