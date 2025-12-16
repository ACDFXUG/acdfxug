package main

func main() {
    var i int
    var j int
    var sum int
    
    sum = 0
    i = 0
    
    for i < 5 {
        j = 0
        for j < 5 {
            sum = sum + i + j
            j = j + 1
        }
        i = i + 1
    }
}

