increment:
    t1 = globalCounter + 1
    globalCounter = t1
    return globalCounter
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
    half = t5
    t6 = exp % 2
    t7 = t6 == 0
    t8 = half * half
    return t8
    t9 = half * half
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
    return a
     =  
main:
    globalCounter = 0
    t13 = call increment, 0
    x = t13
    t14 = call increment, 0
    y = t14
    t15 = call increment, 0
    z = t15
    param 2
    param 10
    t16 = call power, 2
    p = t16
    param 48
    param 18
    t17 = call gcd, 2
    g = t17
    return
     =  
