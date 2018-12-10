package main

import (
    "runtime"
    "fmt"
    "time"
)

func main() {
    PrintMemUsage()

    var overall [][]int
    for i := 0; i<4; i++ {

        a := make([]int, 0, 500000)
        overall = append(overall, a)

        PrintMemUsage()
        time.Sleep(time.Second)
    }

    overall = nil
    PrintMemUsage()

    runtime.GC()
    PrintMemUsage()
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
