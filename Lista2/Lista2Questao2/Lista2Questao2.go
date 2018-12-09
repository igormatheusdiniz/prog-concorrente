package main

import (
	"fmt"
	"time"
	"math/rand"
)

func main() {
	message := reliableRequest()
	fmt.Printf("Primeiro a chegar (%s) \n", message)
}

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

func server(net_chan chan string, name string) {
	rand.Seed(time.Now().UnixNano())
	ranN := rand.Intn(4)
	time.Sleep(time.Duration(ranN) * time.Second)
	fmt.Printf("Produzindo (%s) \n", name)
	net_chan <- name
}
