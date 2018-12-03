package Lista1Questao5;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapVSSynchronizedMap {

	public final static int SIZE = 10;

	public static Map<String, Integer> SynchronizedMap = null;
	public static Map<String, Integer> ConcurrentHashMap = null;

	public static void main(String[] args) throws InterruptedException {

		// Teste com synchronizedMap Object
		SynchronizedMap = Collections.synchronizedMap(new HashMap<String, Integer>());
		teste(SynchronizedMap);

		// Teste com ConcurrentHashMap Object
		ConcurrentHashMap = new ConcurrentHashMap<String, Integer>();
		teste(ConcurrentHashMap);

	}

	public static void teste(final Map<String, Integer> Threads) throws InterruptedException {

		System.out.println("Teste para a classe: " + Threads.getClass());
		long mediaFinal = 0;
		//Repete o teste 5x para manter a confiaça e tirar a média ao final
		for (int i = 0; i < 5; i++) {

			//variavel que armazena o tempo inicial de execucao, importante para poder recuperar o tempo total gasto
			long tempoInicial = System.nanoTime();
			
			//Define o numero de threads que terá acessando e realizando inputs na estrutura
			ExecutorService Server = Executors.newFixedThreadPool(SIZE);
			for (int j = 0; j < SIZE; j++) {
				Server.execute(new Runnable() {
					@Override
					public void run() {

						for (int i = 0; i < 500000; i++) {
							Integer number = (int) Math.ceil(Math.random() * 550000);
							Threads.put(String.valueOf(number), number);
						}
					}
				});
			}

			// Inicia um encerramento ordenado no qual as tarefas enviadas anteriormente são
			// executadas, mas nenhuma nova tarefa será aceita. A invocação não tem efeito
			// adicional se já estiver encerrada.
			Server.shutdown();

			// Bloqueia até que todas as tarefas tenham concluído a execução após uma solicitação de desligamento,
			// o tempo limite ocorre ou o encadeamento atual ser interrompido.
			Server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

			long tempoFinal = System.nanoTime();
			long total = (tempoFinal - tempoInicial) / 1000000L;
			mediaFinal += total;
			System.out.println("500K de entradas adicionadas em " + total + " ms");
		}
		System.out.println("A média de tempo desta execuçao foi de: " + mediaFinal / 5 + " ms\n");
	}
}
