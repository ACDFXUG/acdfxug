	.file	"\305\305\320\362.c++"
	.text
	.section .rdata,"dr"
__ZStL19piecewise_construct:
	.space 1
.lcomm __ZStL8__ioinit,1,1
	.globl	_Array
	.data
	.align 32
_Array:
	.long	1
	.long	8
	.long	6
	.long	4
	.long	10
	.long	5
	.long	3
	.long	2
	.long	22
	.section .rdata,"dr"
LC0:
	.ascii "distant insert\0"
LC1:
	.ascii " \0"
	.text
	.globl	__Z10DIS_INSERTPi
	.def	__Z10DIS_INSERTPi;	.scl	2;	.type	32;	.endef
__Z10DIS_INSERTPi:
LFB1518:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$40, %esp
	movl	$1, -12(%ebp)
L5:
	cmpl	$8, -12(%ebp)
	jg	L2
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	movl	%eax, -24(%ebp)
	movl	-12(%ebp), %eax
	subl	$1, %eax
	movl	%eax, -16(%ebp)
L4:
	cmpl	$0, -16(%ebp)
	js	L3
	movl	-16(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	cmpl	%eax, -24(%ebp)
	jge	L3
	movl	-16(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	-16(%ebp), %edx
	addl	$1, %edx
	leal	0(,%edx,4), %ecx
	movl	8(%ebp), %edx
	addl	%ecx, %edx
	movl	(%eax), %eax
	movl	%eax, (%edx)
	subl	$1, -16(%ebp)
	jmp	L4
L3:
	movl	-16(%ebp), %eax
	addl	$1, %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%eax, %edx
	movl	-24(%ebp), %eax
	movl	%eax, (%edx)
	addl	$1, -12(%ebp)
	jmp	L5
L2:
	movl	$LC0, 4(%esp)
	movl	$__ZSt4cout, (%esp)
	call	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc
	movl	$__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_, (%esp)
	movl	%eax, %ecx
	call	__ZNSolsEPFRSoS_E
	subl	$4, %esp
	movl	$0, -20(%ebp)
L7:
	cmpl	$8, -20(%ebp)
	jg	L6
	movl	-20(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	movl	%eax, (%esp)
	movl	$__ZSt4cout, %ecx
	call	__ZNSolsEi
	subl	$4, %esp
	movl	$LC1, 4(%esp)
	movl	%eax, (%esp)
	call	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc
	addl	$1, -20(%ebp)
	jmp	L7
L6:
	movl	$__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_, (%esp)
	movl	$__ZSt4cout, %ecx
	call	__ZNSolsEPFRSoS_E
	subl	$4, %esp
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1518:
	.section .rdata,"dr"
LC2:
	.ascii "binary insert\0"
	.text
	.globl	__Z10BIN_INSERTPi
	.def	__Z10BIN_INSERTPi;	.scl	2;	.type	32;	.endef
__Z10BIN_INSERTPi:
LFB1519:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$56, %esp
	movl	$1, -12(%ebp)
L16:
	cmpl	$8, -12(%ebp)
	jg	L9
	movl	$0, -16(%ebp)
	movl	-12(%ebp), %eax
	subl	$1, %eax
	movl	%eax, -20(%ebp)
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	movl	%eax, -32(%ebp)
L13:
	movl	-16(%ebp), %eax
	cmpl	-20(%ebp), %eax
	jg	L10
	movl	-16(%ebp), %edx
	movl	-20(%ebp), %eax
	addl	%edx, %eax
	movl	%eax, %edx
	shrl	$31, %edx
	addl	%edx, %eax
	sarl	%eax
	movl	%eax, -36(%ebp)
	movl	-36(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %ecx
	movl	8(%ebp), %eax
	addl	%ecx, %eax
	movl	(%eax), %eax
	cmpl	%eax, %edx
	jle	L11
	movl	-36(%ebp), %eax
	subl	$1, %eax
	movl	%eax, -20(%ebp)
	jmp	L13
L11:
	movl	-36(%ebp), %eax
	addl	$1, %eax
	movl	%eax, -16(%ebp)
	jmp	L13
L10:
	movl	-12(%ebp), %eax
	subl	$1, %eax
	movl	%eax, -24(%ebp)
L15:
	movl	-24(%ebp), %eax
	cmpl	-16(%ebp), %eax
	jl	L14
	movl	-24(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	-24(%ebp), %edx
	addl	$1, %edx
	leal	0(,%edx,4), %ecx
	movl	8(%ebp), %edx
	addl	%ecx, %edx
	movl	(%eax), %eax
	movl	%eax, (%edx)
	subl	$1, -24(%ebp)
	jmp	L15
L14:
	movl	-16(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%eax, %edx
	movl	-32(%ebp), %eax
	movl	%eax, (%edx)
	addl	$1, -12(%ebp)
	jmp	L16
L9:
	movl	$LC2, 4(%esp)
	movl	$__ZSt4cout, (%esp)
	call	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc
	movl	$__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_, (%esp)
	movl	%eax, %ecx
	call	__ZNSolsEPFRSoS_E
	subl	$4, %esp
	movl	$0, -28(%ebp)
L18:
	cmpl	$8, -28(%ebp)
	jg	L17
	movl	-28(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	movl	%eax, (%esp)
	movl	$__ZSt4cout, %ecx
	call	__ZNSolsEi
	subl	$4, %esp
	movl	$LC1, 4(%esp)
	movl	%eax, (%esp)
	call	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc
	addl	$1, -28(%ebp)
	jmp	L18
L17:
	movl	$__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_, (%esp)
	movl	$__ZSt4cout, %ecx
	call	__ZNSolsEPFRSoS_E
	subl	$4, %esp
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1519:
	.section .rdata,"dr"
LC3:
	.ascii "the position \0"
	.text
	.globl	__Z10QUICK_SORTPiii
	.def	__Z10QUICK_SORTPiii;	.scl	2;	.type	32;	.endef
__Z10QUICK_SORTPiii:
LFB1520:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$40, %esp
	movl	12(%ebp), %eax
	cmpl	16(%ebp), %eax
	jge	L30
	movl	12(%ebp), %eax
	movl	%eax, -12(%ebp)
	movl	16(%ebp), %eax
	movl	%eax, -16(%ebp)
	movl	12(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	movl	%eax, -24(%ebp)
L27:
	movl	-12(%ebp), %eax
	cmpl	-16(%ebp), %eax
	jge	L22
L24:
	movl	-12(%ebp), %eax
	cmpl	-16(%ebp), %eax
	jge	L23
	movl	-16(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	cmpl	%eax, -24(%ebp)
	jg	L23
	subl	$1, -16(%ebp)
	jmp	L24
L23:
	movl	-16(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	-12(%ebp), %edx
	leal	0(,%edx,4), %ecx
	movl	8(%ebp), %edx
	addl	%ecx, %edx
	movl	(%eax), %eax
	movl	%eax, (%edx)
L26:
	movl	-12(%ebp), %eax
	cmpl	-16(%ebp), %eax
	jge	L25
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	cmpl	%eax, -24(%ebp)
	jl	L25
	addl	$1, -12(%ebp)
	jmp	L26
L25:
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	-16(%ebp), %edx
	leal	0(,%edx,4), %ecx
	movl	8(%ebp), %edx
	addl	%ecx, %edx
	movl	(%eax), %eax
	movl	%eax, (%edx)
	jmp	L27
L22:
	movl	-12(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%eax, %edx
	movl	-24(%ebp), %eax
	movl	%eax, (%edx)
	movl	$LC3, 4(%esp)
	movl	$__ZSt4cout, (%esp)
	call	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc
	movl	%eax, %edx
	movl	-12(%ebp), %eax
	movl	%eax, (%esp)
	movl	%edx, %ecx
	call	__ZNSolsEi
	subl	$4, %esp
	movl	$__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_, (%esp)
	movl	%eax, %ecx
	call	__ZNSolsEPFRSoS_E
	subl	$4, %esp
	movl	$0, -20(%ebp)
L29:
	cmpl	$8, -20(%ebp)
	jg	L28
	movl	-20(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	movl	%eax, (%esp)
	movl	$__ZSt4cout, %ecx
	call	__ZNSolsEi
	subl	$4, %esp
	movl	$LC1, 4(%esp)
	movl	%eax, (%esp)
	call	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc
	addl	$1, -20(%ebp)
	jmp	L29
L28:
	movl	$__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_, (%esp)
	movl	$__ZSt4cout, %ecx
	call	__ZNSolsEPFRSoS_E
	subl	$4, %esp
	movl	-12(%ebp), %eax
	subl	$1, %eax
	movl	%eax, 8(%esp)
	movl	12(%ebp), %eax
	movl	%eax, 4(%esp)
	movl	8(%ebp), %eax
	movl	%eax, (%esp)
	call	__Z10QUICK_SORTPiii
	movl	-12(%ebp), %eax
	leal	1(%eax), %edx
	movl	16(%ebp), %eax
	movl	%eax, 8(%esp)
	movl	%edx, 4(%esp)
	movl	8(%ebp), %eax
	movl	%eax, (%esp)
	call	__Z10QUICK_SORTPiii
	jmp	L19
L30:
	nop
L19:
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1520:
	.def	___main;	.scl	2;	.type	32;	.endef
	.globl	_main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
LFB1521:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	andl	$-16, %esp
	addl	$-128, %esp
	call	___main
	movl	_Array, %eax
	movl	%eax, 92(%esp)
	movl	_Array+4, %eax
	movl	%eax, 96(%esp)
	movl	_Array+8, %eax
	movl	%eax, 100(%esp)
	movl	_Array+12, %eax
	movl	%eax, 104(%esp)
	movl	_Array+16, %eax
	movl	%eax, 108(%esp)
	movl	_Array+20, %eax
	movl	%eax, 112(%esp)
	movl	_Array+24, %eax
	movl	%eax, 116(%esp)
	movl	_Array+28, %eax
	movl	%eax, 120(%esp)
	movl	_Array+32, %eax
	movl	%eax, 124(%esp)
	movl	_Array, %eax
	movl	%eax, 56(%esp)
	movl	_Array+4, %eax
	movl	%eax, 60(%esp)
	movl	_Array+8, %eax
	movl	%eax, 64(%esp)
	movl	_Array+12, %eax
	movl	%eax, 68(%esp)
	movl	_Array+16, %eax
	movl	%eax, 72(%esp)
	movl	_Array+20, %eax
	movl	%eax, 76(%esp)
	movl	_Array+24, %eax
	movl	%eax, 80(%esp)
	movl	_Array+28, %eax
	movl	%eax, 84(%esp)
	movl	_Array+32, %eax
	movl	%eax, 88(%esp)
	movl	_Array, %eax
	movl	%eax, 20(%esp)
	movl	_Array+4, %eax
	movl	%eax, 24(%esp)
	movl	_Array+8, %eax
	movl	%eax, 28(%esp)
	movl	_Array+12, %eax
	movl	%eax, 32(%esp)
	movl	_Array+16, %eax
	movl	%eax, 36(%esp)
	movl	_Array+20, %eax
	movl	%eax, 40(%esp)
	movl	_Array+24, %eax
	movl	%eax, 44(%esp)
	movl	_Array+28, %eax
	movl	%eax, 48(%esp)
	movl	_Array+32, %eax
	movl	%eax, 52(%esp)
	leal	92(%esp), %eax
	movl	%eax, (%esp)
	call	__Z10DIS_INSERTPi
	leal	56(%esp), %eax
	movl	%eax, (%esp)
	call	__Z10BIN_INSERTPi
	movl	$8, 8(%esp)
	movl	$0, 4(%esp)
	leal	20(%esp), %eax
	movl	%eax, (%esp)
	call	__Z10QUICK_SORTPiii
	movl	$0, %eax
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1521:
	.def	___tcf_0;	.scl	3;	.type	32;	.endef
___tcf_0:
LFB1999:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$8, %esp
	movl	$__ZStL8__ioinit, %ecx
	call	__ZNSt8ios_base4InitD1Ev
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1999:
	.def	__Z41__static_initialization_and_destruction_0ii;	.scl	3;	.type	32;	.endef
__Z41__static_initialization_and_destruction_0ii:
LFB1998:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$24, %esp
	cmpl	$1, 8(%ebp)
	jne	L36
	cmpl	$65535, 12(%ebp)
	jne	L36
	movl	$__ZStL8__ioinit, %ecx
	call	__ZNSt8ios_base4InitC1Ev
	movl	$___tcf_0, (%esp)
	call	_atexit
L36:
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE1998:
	.def	__GLOBAL__sub_I_Array;	.scl	3;	.type	32;	.endef
__GLOBAL__sub_I_Array:
LFB2000:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$24, %esp
	movl	$65535, 4(%esp)
	movl	$1, (%esp)
	call	__Z41__static_initialization_and_destruction_0ii
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
LFE2000:
	.section	.ctors,"w"
	.align 4
	.long	__GLOBAL__sub_I_Array
	.ident	"GCC: (MinGW.org GCC Build-2) 9.2.0"
	.def	__ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc;	.scl	2;	.type	32;	.endef
	.def	__ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_;	.scl	2;	.type	32;	.endef
	.def	__ZNSolsEPFRSoS_E;	.scl	2;	.type	32;	.endef
	.def	__ZNSolsEi;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitD1Ev;	.scl	2;	.type	32;	.endef
	.def	__ZNSt8ios_base4InitC1Ev;	.scl	2;	.type	32;	.endef
	.def	_atexit;	.scl	2;	.type	32;	.endef
