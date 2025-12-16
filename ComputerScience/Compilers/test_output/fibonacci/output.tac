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
    i = 0
L3:
    t7 = i < 10
    param i
    t8 = call fib, 1
    result = t8
    t9 = i + 1
    i = t9
    if t7 goto L3
L4:
    return
     =  
