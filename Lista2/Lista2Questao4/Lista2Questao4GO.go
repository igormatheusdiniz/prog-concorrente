package main

import (
    "runtime"
    "fmt"
    "time"
)

func main() {
    PrintMemUsage()

    for i := 1; i<3000; i++ {
		fmt.Print(i)
		for j := 0; j<i; j++{
			go function()
		}
    PrintMemUsage()
	runtime.GC()
}
}


func function(){
	time.Sleep(20 * time.Millisecond)

}

func PrintMemUsage() {
        var m runtime.MemStats
        runtime.ReadMemStats(&m)
        fmt.Printf("\tUsedMemory is Megabyte = %v MiB", bToMb(m.TotalAlloc))
        fmt.Printf("\tSys = %v MiB \n", bToMb(m.Sys))
        
}

func bToMb(b uint64) uint64 {
    return b / 1024 / 1024
}
