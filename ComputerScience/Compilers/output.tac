add:
    t1 = a + b
    return t1
     =  
multiply:
    t2 = x * y
    return t2
     =  
main:
    a = 10
    b = 20
    param a
    param b
    t3 = call add, 2
    sum = t3
    param a
    param b
    t4 = call multiply, 2
    product = t4
    t5 = sum + product
    result = t5
    return
     =  
