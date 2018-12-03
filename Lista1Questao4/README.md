# IDEIA: 

Executar reliableRequest() em um loop while até que uma variável mude. Criar um fluxo alternativo que altere a variável de "checada" dentro do loop. Utilizar wait no loop e notify no fluxo alternativo.

# FUNCIONAMENTO:

A função loopReliableRequest é responsável pela execução em loop de reliableRequest:

```Java
	public synchronized void loopReliableRequest() {
		while (true){
			try {
				this.wait(1000);
				if(this.licenseToKill) { //Saiu o wait por causa do timeout ou alguém mudou a variável e deu notifyAll?
					System.out.println("BOND GIRL, KILL CONFIRMED");
					break;
				}
				String taken = reliableRequest(0);
				System.out.println(taken);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}			
	}
```
A função reliableRequest() é executada a cada segundo, até que a variável licenseToKill seja alterada para True. O código fica bloqueado em `this.wait(1000);` até que se passe o tempo definido como timeout ou até que alguém execute notifyAll no objeto this.


A variável é alterada pelo fluxo alternativo após um tempo indeterminado:
```Java
public void run() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(10000));
		} catch (InterruptedException e1) {
			System.out.println(e1.getMessage());
		}
		// 007, Licence to Kill
		synchronized (object){
			if(object != null) {
				System.out.println("007, KILL");
				object.setLicenseToKill(true);
				object.notifyAll();
			}			
		}
	}
```


Após a variável ser alterada um notifyAll é chamado para que a ação tenha efeito imediato.
