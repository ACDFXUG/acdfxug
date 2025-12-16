package main

func fib(n int) int {
    if n <= 1 {
        return n
    }
    return fib(n - 1) + fib(n - 2)
}

func main() {
    var i int
    i = 0
    
    for i < 10 {
        result := fib(i)
        i = i + 1
    }
}

