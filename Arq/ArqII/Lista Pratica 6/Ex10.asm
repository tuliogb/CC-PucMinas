.text
.globl start

#s0 >> x
#s1 >> z

start:
	ori $t0, $zero, 0x1001	#t0 = 0x1001
	sll $t0, $t0, 16	#t0 = 0x10010000
	
	lw $s0, ($t0)		#s0 = x
	lw $s1, 4($t0)		#s1 = z
	lw $s2, 8($t0) 		#$s2 = y
	
	sll $t1, $s0, 7		#t0 = 128x
	sub $t1, $t1, $s0	#t0 = 127x
	
	sll $t2, $s1, 6		#t1 = 64z
	add $t2, $t2, $s1	#t1 = 65z
		
	sub $t3, $t1, $t2	#t2 = 127x - 65z
	addi $s2, $t3, 1	#s2 = 127x - 65z + 1 

	sw $s2, 8($t0)		#MEM[10010012]=soma(s2)