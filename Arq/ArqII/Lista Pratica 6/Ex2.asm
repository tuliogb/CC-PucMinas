.text
.globl start


#x >> s0
#y >> s1


start:
	ori $s0,$zero,1
	add $t0, $s0, $s0	#t0=2x
	add $t1, $t0, $t0	#t1=4x
	add $s1, $t1, $s0	#y=4x+x
	addi $s1, $s1, 15	#y=5x+15   
	
#fim	