.data

x1: .word 15
x2: .word 25
x3: .word 13
x4: .word 17
soma: .word -1

.text
.globl start


start:
	ori $s0,$zero,0x1001	#carregaEndereco
	sll $s0,$s0,16
	
	lw $s1,0($s0)		#s1=men[0]
	lw $s2,4($s0)		#s2=men[1]
	lw $s3,8($s0)		#s3=men[2]
	lw $s4,12($s0)		#s4=men[3]
		
	add $s5, $s1, $s2 	
	add $s5, $s5, $s3	
	add $s5, $s5, $s4 
	
	sw $s5,16($s0)		#men[4]=s5
#fim
