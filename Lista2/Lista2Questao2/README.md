# Ideia:

A questão pode ser solucionada utilizando a mesma ideia de canal da questão anterior, adicionando apenas o detalhe de analise de tempo.

# Funcionamento:

## 1- O cliente(main) chama a função reliable request que está presente dentro da API e atribui a varáivel mensagem

 
```Go

 func main() {
	message := reliableRequest()
	fmt.Printf("Primeiro a chegar (%s) \n", message)
}
```

## 2- A função reliableRequest() cria o canal e as goRoutines, da a direção de que as goRoutines depositem sua url no canal.

O método reliable request cria um canal e as goRoutines, onde cada uma seria uma requisição. São configuradas para utilizar o canal criado para troca de informações. Após a troca de informações é comunicado a hora do consumo e a mensagem é retornada.
Um ponto importante a ser ressaltado é que um timeout é definido inicialmente como 2 segundos, e diferente da questão anterior, se faz necessário o uso de SELECT, pois CASE ele consiga retornar a mensagem em menos de 2 segundos o andamento do programa ocorre segundo o esperado, entretanto se passar do CASE maior que 2 segundos deve ocorrer timeout

```Go
func reliableRequest() string {
	timeout := time.After(2 * time.Second)
	net_ch := make(chan string)
	go server(net_ch, "mirror1.com")
	go server(net_ch, "mirror2.br")
	go server(net_ch, "mirror3.edu")
	message := ""
	select {
		case message = <- net_ch:
			fmt.Print("Produziu \n")
		case <- timeout:
			fmt.Print("Timeout \n")
			message = "Timeout"
	}
	return message
	
}
```
  

## 3- O servidor deposita informações no canal incluindo um certo tempo de sleep aleatório.

Para garantir a aleatoriedade e testar os timeouts, determinamos um tempo aleatorio de sleep, para que quando a thread "acorde" faça algo, e dependendo do tempo que ela dormiu possa dar timeout ou não.

```Go
 func server(net_chan chan string, name string) {
	rand.Seed(time.Now().UnixNano())
	ranN := rand.Intn(4)
	time.Sleep(time.Duration(ranN) * time.Second)
	fmt.Printf("Produzindo (%s) \n", name)
	net_chan <- name
}

  ```
