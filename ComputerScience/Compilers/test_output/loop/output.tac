sumToN:
    sum = 0
    i = 0
L1:
    t1 = i <= n
    t2 = sum + i
    sum = t2
    t3 = i + 1
    i = t3
    if t1 goto L1
L2:
    return sum
     =  
main:
    param 100
    t4 = call sumToN, 1
    total = t4
    return
     =  
