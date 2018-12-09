package main

import (
	"fmt"
)

func main() {
	message := reliableRequest()
	fmt.Printf("Primeiro a chegar (%s) \n", message)
}

func reliableRequest() string {
	net_ch := make(chan string)
	go server(net_ch, "mirror1.com")
	go server(net_ch, "mirror2.br")
	go server(net_ch, "mirror3.edu")
	message := <- net_ch
	return message
	
}

func server(net_chan chan string, name string) {
	fmt.Printf("Produzindo (%s) \n", name)
	net_chan <- name
}
