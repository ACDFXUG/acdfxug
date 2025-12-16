main:
    sum = 0
    i = 0
L1:
    t1 = i < 5
    j = 0
L3:
    t2 = j < 5
    t3 = sum + i
    t4 = t3 + j
    sum = t4
    t5 = j + 1
    j = t5
    if t2 goto L3
L4:
    t6 = i + 1
    i = t6
    if t1 goto L1
L2:
    return
     =  
