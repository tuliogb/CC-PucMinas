.data
A: .word -9

#t0 -> first address
#A -> $s0

.text
.globl start


	start: 
		ori $t0, $0, 0x1001	#t0 = 0x1001
		sll $t0, $t0, 16	#t0 = 0x10010000
	
		lw $s0, 0($t0) 		#s0 = A
		sra $t1, $s0, 31	#t1 = s0 >> 31 (separa o bit de sinal)
		beq $t1, $0, positivo	#if(t1==0) goTo positivo 
		sub $s0, $0, $s0	#s0 = 0 - s0
	
	positivo:
		sw $s0, 0($t0)		#mem[t0] = s0