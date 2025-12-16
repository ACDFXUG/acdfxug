package main

func add(a int, b int) int {
    return a + b
}

func multiply(x int, y int) int {
    return x * y
}

func main() {
    var a int
    var b int
    
    a = 10
    b = 20
    
    sum := add(a, b)
    product := multiply(a, b)
    
    result := sum + product
}

