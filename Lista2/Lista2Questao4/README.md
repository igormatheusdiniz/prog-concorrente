## Ideia
Criar um o mesmo programa em linguagens diferentes e comparar o consumo de memória nos dois casos. O programa consistia de algo bem simples,  criar threads e goRoutines que executavam um comando de sleep.


## Alguns Resultados do experimento

Java

[MAIS RESULTADOS PRA CIMA]

Qte Threads:136
Used memory is bytes: 20893976
Used memory is megabytes: 19

Qte Threads:137
Used memory is bytes: 30435152
Used memory is megabytes: 29

Qte Threads:138
Used memory is bytes: 23622600
Used memory is megabytes: 22

Qte Threads:139
Used memory is bytes: 30438480
Used memory is megabytes: 29

Qte Threads:140
Used memory is bytes: 39980712
Used memory is megabytes: 38

[MAIS RESULTADOS PRA BAIXO]

GO

[MAIS RESULTADOS PRA CIMA]

136     UsedMemory is Megabyte = 1 MiB  Sys = 19 MiB

137     UsedMemory is Megabyte = 1 MiB  Sys = 19 MiB

138     UsedMemory is Megabyte = 1 MiB  Sys = 19 MiB

139     UsedMemory is Megabyte = 1 MiB  Sys = 19 MiB

140     UsedMemory is Megabyte = 1 MiB  Sys = 19 MiB

[MAIS RESULTADOS PRA BAIXO]


## Discussão do resultado
Foram coletadas para a execução de até 3000 threads e goRoutines, e percebeu-se que para amostra coletada acima, e pra os demais valores do experimento, GO consome bem menos memória que Java Thread

## Threads x Goroutine
De maneira bem generica podemos dizer que a criação de uma goroutine exige pouca memória pois elas crescem alocando e liberando o armazenamento no heap conforme necessário. Já as Threads em Java são mapeadas diretamente para as threads do sistema operacional, e portanto relativamente pesadas. As threads em Java são pesadas devido o seu stack de tamanho fixo diminuindo assim o número de threads que você pode executar em uma única máquina virtual, uma vez que existe uma sobrecarga de memória.

Já a goroutine tem um stack segmentado que cresce de acordo com a demanda e isso faz com que a criação de uma goroutine exija pouca memória. Elas crescem alocando e liberando o armazenamento no heap conforme necessário.
