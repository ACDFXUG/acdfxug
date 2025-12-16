fib:
    t1 = n <= 1
    return n
    ifFalse t1 goto L1
L1:
    t2 = n - 1
    param t2
    t3 = call fib, 1
    t4 = n - 2
    param t4
    t5 = call fib, 1
    t6 = t3 + t5
    return t6
     =  
main:
L3:
    t7 = 0 < 10
    param 0
    t8 = call fib, 1
    if t7 goto L3
L4:
    return
     =  
