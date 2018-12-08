# IDEIA: 

Utilizar um parametro na função wait para que ele deixe de bloquar após decorrido um certo tempo desde o bloqueio. 

# FUNCIONAMENTO:

A principal modificação está na função takeMessage, do channel:

```Java
public synchronized  String takeMessage(int timeout) {
		String taken = null;
		while (this.isEmpty()) {
			try {
				this.wait(timeout);
				if(this.isEmpty()) { //DESBLOQUEIO POR TIMEOUT OU NOTIFY?
					taken = "Timeout!!";
					break;
				}
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		if(taken == null) {
			taken = messages.get(0);
			this.messages.remove(0);
		} 
		this.notifyAll();
		return taken;
	}
  
```

Se não existe nada a ser retornado a função bloqueia, na linha  `this.wait(timeout);`

A execução é desbloqueada após:

  1. Excedido o timeout;
  
  1. Um servidor adicionar algo no canal e chamar notify
  
  
Quando o desbloqueio acontecer é necessário saber qual foi a razão de tal desbloqueio.
Se o canal ainda estiver vazio significa que foi o timeout.
Em cada de Timeout é retornada e a execução interrompida e a API, que chamou takeMessage na função reliableRequest é liberada para repassar a informação de TIMEOUT para o cliente.  

