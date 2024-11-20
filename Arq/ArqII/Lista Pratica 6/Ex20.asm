.data
x: .word 5

.text
.globl start

	start: 
		ori $t0, $0, 0x1001	#t0 -> 0x10010000 (first position)
		sll $t0, $t0, 16	#t0 << 16
	
		lw $s0, 0($t0)		#s0 = MEM[$t0]
		andi $t1, $s0, 1	#t1 = s0 & 1
		mult $s0, $s0		#s0 * s0
		mflo $t2		#t2 = x²
		mult $t2, $s0		#t2 * s0
		mflo $t3		#t3 = x³
		mult $t3, $s0		#t3 * s0
		mflo $t4		#t4 = x⁴
		mult $t4, $s0		#t5 * s0	
		mflo $t5		#t5 = x⁵
		bne $t1, $0, impar	#if(t1!=0){ goto impar }
		add $t2, $t2, $t2	#t2 = t2 + t2
		add $s1, $t4, $t3	#s1 = t4 + t3
		sub $s1, $s1, $t2	#s1 = s1 - t2
		j fim			#goto fim
		
	impar:	
		sub $s1, $t5, $t3	#s1 = t5 - t3
		addi $s1, $s1, 1	#s1 = s1 + 1
		
	fim:	
		sw $s1, 4($t0)		#MEM[4+$t0] = s1