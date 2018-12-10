# Ideia:

A questão pode ser solucionada utilizando a ideia de canal, e em GO tem-se um canal nativo que precisa apenas ser criado e repassado para as threads criadas. O canal trata as questões concorrentes de implementação e é utilizado para abstrair a troca de informações entre a API e os servidores. O servidor dorme por um tempo indeterminado e então retorna algo.
Cada thread criada na API é como uma request HTTP. 

# Funcionamento:

## 1- O cliente(main) chama a função reliable request que está presente dentro da API e atribui a varáivel mensagem

 
```Go

 func main() {
	message := reliableRequest()
	fmt.Printf("Primeiro a chegar (%s) \n", message)
}
```

## 2- A função reliableRequest() cria o canal e as threads, da a direção de que as threads depositem sua url no canal. Por ultimo sinaliaz a hora do consumo e retorna a mensagem que foi consumida.

O método reliable request cria um canal e as threads, onde cada uma seria uma requisição. As threads são configuradas para utilizar o canal criado para troca de informações. Após a troca de informações é comunicado a hora do consumo e a mensagem é retornada.

```Go
func reliableRequest() string {
	net_ch := make(chan string)
	go server(net_ch, "mirror1.com")
	go server(net_ch, "mirror2.br")
	go server(net_ch, "mirror3.edu")
	message := <- net_ch
	return message
	
}
```
  

## 3- O servidor deposita informações no canal.

```Go
func server(net_chan chan string, name string) {
	fmt.Printf("Produzindo (%s) \n", name)
	net_chan <- name
}
  
  ```
