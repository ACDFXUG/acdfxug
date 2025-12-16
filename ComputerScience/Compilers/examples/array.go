package main

func main() {
    var arr [10]int
    var i int
    
    i = 0
    for i < 10 {
        arr[i] = i * 2
        i = i + 1
    }
    
    x := arr[0]
    y := arr[5]
    sum := x + y
}

