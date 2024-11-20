.data 
x: .word 100000
z: .word 200000
y: .word 0

.text
.globl start	

#x -> $s0
#z -> $s1
#y -> $s2

start:

	
	ori $t0, $0, 0x1001	#t0 = enderecoBase
	sll $t0, $t0, 16
	
	lw $s0, 0($t0)		#s0 = x
	lw $s1, 4($t0)		#s1 = z
	lw $s2, 8($t0)		#s2 = y
	
	sub $t1, $s0, $s1	#t1 = x-z
	ori $t2, $zero 0x493E	#t2 = 0x493E
	sll $t2, $t2, 4		#t2 = 0x493E0 (300000)
	
	add $s2, $t1, $t2	#y = x-z + 300000
	sw $s2, 8($t0)		#men[8]=y