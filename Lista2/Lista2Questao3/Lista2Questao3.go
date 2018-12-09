package main

import (
	"fmt"
	"time"
	"math/rand"
)

func main() {
	kill_permision := make(chan int)
	go bond_girl(kill_permision)
	ticker := time.Tick(500 * time.Millisecond)
	loop:
	for {
        select {
        case <-kill_permision:
			fmt.Print("007 killed")
            break loop;
        case <-ticker:
            message := reliableRequest()
            fmt.Printf("Primeiro a chegar (%s) \n", message)
        }
    }
	
}

func reliableRequest( ) string {
	net_ch := make(chan string)
	go server(net_ch, "mirror1.com")
	go server(net_ch, "mirror2.br")
	go server(net_ch, "mirror3.edu")
	message := ""
	select {
		case message = <- net_ch:
		case <-time.After(3 * time.Second):
			message = "Timeout"
	}
	return message
	
}

func server(net_chan chan string, name string) {
	rand.Seed(time.Now().UnixNano())
	ranN := rand.Intn(5)
	time.Sleep(time.Duration(ranN) * time.Second)
	fmt.Printf("Produzindo (%s) \n", name)
	net_chan <- name
}


func bond_girl(kill_permision chan int) {
	rand.Seed(time.Now().UnixNano())
	ranN := rand.Intn(5)+10
	time.Sleep(time.Duration(ranN) * time.Second)
	kill_permision <- -1 //JAMES, KILL!
}
