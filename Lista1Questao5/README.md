# Questão5 Letra A
# Ideia do experimento:

A ideia é criar uma instancia de um SynchronizedMap e uma instância de uma ConcurrentHashMap e fazer teste sobre estas estruturas. Realizamos o mesmo experimento 10x para cada estrutura e tiramos a média dos resultados obtidos para poder ter uma medida mais confiável, além disso dentro do escopo do "for" no qual determinamos as 10 repetições, criamos uma ExecutorService com a finalidade de criar as threads que estariam realizando as operações de inserção na estrutura, o número de threads que foi testado foi de 2,5,10 trabalhando nos procedimentos de inserção. Ao final da execução o tempo inicial que havia sido capturado é subtraido do tempo final capturado logo ao termino da execução, e printado na tela. A partir destes dados nós podemos coletar alguns dados.

A inspiração para este experimento sugiu de buscas  por ideias e material auxiliar na internet e então encontramos este link [aqui](https://crunchify.com/hashmap-vs-concurrenthashmap-vs-synchronizedmap-how-a-hashmap-can-be-synchronized-in-java/)

# Funcionamento:

## 1- Para ter um resultado mais seguro definimos um numero de 10 execuções
 
 Com o intuito de acompanhar melhor o que está acontecendo no código, decidiu-se executar o teste em questão 10x, ao termino de todas elas, é realizada uma média de todas as execuções.

## 2- Algumas variáveis auxiliares são criadas.

mediaFinal :: Armazenará o valor da média das execuções
tempoInicial :: Captura o tempo do sistema em que as execuções começaram
size :: Importante para definir a quantidade de threads que estarao inserindo dados na estrutura de dados
tempoFinal :: Captura o tempo do sistema em que as execuções terminam
total :: Media do tempo gasto para finalizar cada execução

## 3- ExecutorService e operações de inserção

Como mencionado anteriormente, uma variavel denominada SIZE armazena o valor de quantidade de threads geradas pelo ExecutorService para trabalhar nas inserções na estrutura, trabalhamos com os valores de 2,5,10.
No que diz respeito as inserções, simulamos um ambiente onde haveriam 500k inserções dentro da estrutura instanciada

```Java
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
```
## 4- Plots de resultados obtidos

1. Resultados de 10 execuções com 2 threads realizandos inputs

Os dados que seguem abaixo são dados copiados da execução do programa:

Teste para a classe: class java.util.Collections$SynchronizedMap

500K de entradas adicionadas em 488 ms

500K de entradas adicionadas em 404 ms

500K de entradas adicionadas em 812 ms

500K de entradas adicionadas em 384 ms

500K de entradas adicionadas em 408 ms

500K de entradas adicionadas em 417 ms

500K de entradas adicionadas em 483 ms

500K de entradas adicionadas em 356 ms

500K de entradas adicionadas em 418 ms

500K de entradas adicionadas em 345 ms

A média de tempo desta execuçao foi de: 451 ms

Teste para a classe: class java.util.concurrent.ConcurrentHashMap

500K de entradas adicionadas em 364 ms

500K de entradas adicionadas em 197 ms

500K de entradas adicionadas em 192 ms

500K de entradas adicionadas em 312 ms

500K de entradas adicionadas em 191 ms

500K de entradas adicionadas em 189 ms

500K de entradas adicionadas em 280 ms

500K de entradas adicionadas em 188 ms

500K de entradas adicionadas em 189 ms

500K de entradas adicionadas em 190 ms

A média de tempo desta execuçao foi de: 229 ms

--------

2. Resultados obtidos de 10 execuções com 5 Threads realizando inputs

Teste para a classe: class java.util.Collections$SynchronizedMap

500K de entradas adicionadas em 1508 ms

500K de entradas adicionadas em 887 ms

500K de entradas adicionadas em 901 ms

500K de entradas adicionadas em 916 ms

500K de entradas adicionadas em 893 ms

500K de entradas adicionadas em 917 ms

500K de entradas adicionadas em 902 ms

500K de entradas adicionadas em 855 ms

500K de entradas adicionadas em 910 ms

500K de entradas adicionadas em 899 ms

A média de tempo desta execuçao foi de: 958 ms

Teste para a classe: class java.util.concurrent.ConcurrentHashMap

500K de entradas adicionadas em 456 ms

500K de entradas adicionadas em 461 ms

500K de entradas adicionadas em 414 ms

500K de entradas adicionadas em 315 ms

500K de entradas adicionadas em 421 ms

500K de entradas adicionadas em 317 ms

500K de entradas adicionadas em 428 ms

500K de entradas adicionadas em 320 ms

500K de entradas adicionadas em 412 ms

500K de entradas adicionadas em 317 ms

A média de tempo desta execuçao foi de: 386 ms

----------
3. Resultados obtidos de 10 execuções com 10 Threads realizando inputs

Teste para a classe: class java.util.Collections$SynchronizedMap

500K de entradas adicionadas em 2363 ms

500K de entradas adicionadas em 1854 ms

500K de entradas adicionadas em 1967 ms

500K de entradas adicionadas em 1774 ms

500K de entradas adicionadas em 1813 ms

500K de entradas adicionadas em 1824 ms

500K de entradas adicionadas em 1712 ms

500K de entradas adicionadas em 1708 ms

500K de entradas adicionadas em 1720 ms

500K de entradas adicionadas em 1814 ms

A média de tempo desta execuçao foi de: 1854 ms

Teste para a classe: class java.util.concurrent.ConcurrentHashMap

500K de entradas adicionadas em 1354 ms

500K de entradas adicionadas em 748 ms

500K de entradas adicionadas em 771 ms

500K de entradas adicionadas em 757 ms

500K de entradas adicionadas em 731 ms

500K de entradas adicionadas em 651 ms

500K de entradas adicionadas em 754 ms

500K de entradas adicionadas em 867 ms

500K de entradas adicionadas em 747 ms

500K de entradas adicionadas em 653 ms

A média de tempo desta execuçao foi de: 803 ms

--------

![Plot Comparação](https://github.com/igormatheusdiniz/prog-concorrente/blob/master/Lista1Questao5/image.png)

# 5- Discussão dos resultados obtidos

Podemos perceber que em todos os casos e em todos os cenários testados neste experimento, o ConcurrentHashMap possuiu um desempenho melhor. Isso era o esperado uma vez que ConcurrentHashMap bloque apenas uma parte dos dados que estão sendo atualizados, enquanto outra parte dos dados pode ser acessada por outros threads. Por outro lado synchronizedMap() bloquea todos os dados durante a atualização, e outras threads só poderão acessar os dados quando o bloqueio for liberado. Dessa forma podemos dizer que o SynchronizedMap pode ocasionar um efeito de contenção.
