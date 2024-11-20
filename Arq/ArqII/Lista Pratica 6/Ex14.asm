.data
A: .word 7

#t0 -> first address		
#A -> $s0
#isEven -> $s1

.text
.globl start

	start: 
		ori $t0, $0, 0x1001	#t0 = 0x1001
		sll $t0, $t0, 16	#t0 = 0x10010000
		lw $s0, 0($t0) 		#s0 = A
		andi $t1, $s0, 1	#t1 = s0 & 0x00000001
		bne $t1, $0, else	#if(t1!=0){ goto else; } 
		
	if:		
		and $s1, $0, $0		#s1 = 0 & 0
		j fim			#goto fim
		
	else:	
		ori $s1, $0, 1		#s1 = 0 | 1
		
	fim:	
		sw $s1, 4($t0)		#men[4+t0] = s1