; Generated x86-64 Assembly Code
; Compiler: Go Language Compiler

section .data
    _str0: db "Hello, World!", 0
    _fmt_int: db "%d", 10, 0
    _fmt_float: db "%f", 10, 0
    _fmt_str: db "%s", 10, 0

section .text
    global main
    extern printf

    # main:
main:
    push rbp
    mov rbp, rsp
    sub rsp, 256    ; Allocate stack space

    # param "Hello, World!"
    mov rax, [rbp-8]
    push rax

    # t1 = call println, 1
    call println
    mov [rbp-16], rax

    # return
    mov rsp, rbp
    pop rbp
    ret

    # =

