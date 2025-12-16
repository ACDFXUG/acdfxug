factorial:
    t1 = n <= 1
    return 1
    t2 = n - 1
    param t2
    t3 = call factorial, 1
    t4 = n * t3
    return t4
    ifFalse t1 goto L1
    goto L2
L1:
L2:
    return
     =  
main:
    n = 5
    param 5
    t5 = call factorial, 1
    return
     =  
