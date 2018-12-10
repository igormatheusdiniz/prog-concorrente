## Threads x Goroutine
De maneira bem generica podemos dizer que a criação de uma goroutine exige pouca memória pois elas crescem alocando e liberando o armazenamento no heap conforme necessário. Já as Threads em Java são mapeadas diretamente para as threads do sistema operacional, e portanto relativamente pesadas. As threads em Java são pesadas devido o seu stack de tamanho fixo diminuindo assim o número de threads que você pode executar em uma única máquina virtual, uma vez que existe uma sobrecarga de memória.

Já a goroutine tem um stack segmentado que cresce de acordo com a demanda e isso faz com que a criação de uma goroutine exija pouca memória. Elas crescem alocando e liberando o armazenamento no heap conforme necessário.
