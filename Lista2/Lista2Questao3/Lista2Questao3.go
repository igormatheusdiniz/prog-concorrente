package main

import (
	"fmt"
	"time"
	"math/rand"
)

func main() {
	kill_permision := make(chan int)
	go bond_girl(kill_permision)
	message := reliableRequest(kill_permision)
	fmt.Printf("Primeiro a chegar (%s) \n", message)
}

func reliableRequest( kill_permision chan int ) string {
	net_ch := make(chan string)
	go server(net_ch, "mirror1.com")
	go server(net_ch, "mirror2.br")
	go server(net_ch, "mirror3.edu")
	message := ""
	select {
		case message = <- net_ch:
		case <-time.After(10 * time.Second):
			message = "Timeout"
		case <-kill_permision:
			message = "007 killed"
	}
	return message
	
}

func server(net_chan chan string, name string) {
	var ranN = rand.Intn(10)
	time.Sleep(time.Duration(ranN) * time.Second)
	fmt.Printf("Produzindo (%s) \n", name)
	net_chan <- name
}


func bond_girl(kill_permision chan int) {
	var ranN = rand.Intn(3)
	time.Sleep(time.Duration(ranN) * time.Second)
	kill_permision <- -1 //JAMES, KILL!
}
