.data
x: .word 3
y: .word 4

#t0 -> 0x10010000 (enderecoBase)
#x -> s0
#y -> s1
#k -> s2

.text
.globl start

	start: 
		ori $t2, $0, 0x1001	#t2 = 0x1001 
		sll $t2, $t2 16		#t2 = 0x10010000
		lw $s0, 0($t2)		#s0 = MEM[$t0]
		lw $s1, 4($t2)		#s1 = MEM[$t0+4]
		or $t3, $s0, $0		#t3 = x
		or $t4, $s1, $0		#t4 = y
		or $t0, $0, $0		#t0 = 0 (contador de x)
		or $t1, $0, $0		#t1 = 0 (contador de y)
		
	if:	
		beq $t3, $0, if2	#if(t3==0){ goto fim1}
		addi $t0, $t0, 1	#t0 = t0 + 1
		srl $t3, $t3, 1		#t3 >> 1
		j if			#goto if
		
	if2:	
		beq $t4, $0, fim	#if(t4==0){ goto fim2 }
		addi $t1, $t1, 1	#t1 = t1 + 1
		srl $t4, $t4, 1		#t4 >> 1
		j if2			#goto if
		
	fim:	
		mult $t0, $t1		#t0 * t1
		mflo $t5		#t5 = lo
		slti  $t6, $t5, 32	#if(t5<32){ t6=1 } else { t6 = 0 }
		beq $t6, $0, maior	#if(t6!=1){ goto maior }
		or $s2, $0, $t5		#s2 = t5
		mflo $s2		#s2 = lo
		j fim2			#goto fim
		
	maior:		
		mfhi $s2		#s2 = hi
		mflo $s3		#s3 = lo
	fim2:	