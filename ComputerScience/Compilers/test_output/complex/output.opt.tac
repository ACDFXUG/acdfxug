increment:
    t1 = globalCounter + 1
    globalCounter = t1
    return t1
     =  
power:
    t2 = exp == 0
    return 1
    ifFalse t2 goto L1
L1:
    t3 = exp == 1
    return base
    ifFalse t3 goto L3
L3:
    t4 = exp / 2
    param base
    param t4
    t5 = call power, 2
    t6 = exp % 2
    t7 = t6 == 0
    t8 = t5 * t5
    return t8
    t9 = t5 * t5
    t10 = t9 * base
    return t10
    ifFalse t7 goto L5
    goto L6
L5:
L6:
    return
     =  
gcd:
L7:
    t11 = b != 0
    temp = b
    t12 = a % b
    b = t12
    a = temp
    if t11 goto L7
L8:
    return b
     =  
main:
    globalCounter = 0
    t13 = call increment, 0
    t14 = call increment, 0
    t15 = call increment, 0
    param 2
    param 10
    t16 = call power, 2
    param 48
    param 18
    t17 = call gcd, 2
    return
     =  
