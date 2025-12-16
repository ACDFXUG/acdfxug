package main

var globalCounter int

func increment() int {
    globalCounter = globalCounter + 1
    return globalCounter
}

func power(base int, exp int) int {
    if exp == 0 {
        return 1
    }
    
    if exp == 1 {
        return base
    }
    
    half := power(base, exp / 2)
    
    if exp % 2 == 0 {
        return half * half
    } else {
        return half * half * base
    }
}

func gcd(a int, b int) int {
    for b != 0 {
        temp := b
        b = a % b
        a = temp
    }
    return a
}

func main() {
    globalCounter = 0
    
    x := increment()
    y := increment()
    z := increment()
    
    p := power(2, 10)
    
    g := gcd(48, 18)
}

