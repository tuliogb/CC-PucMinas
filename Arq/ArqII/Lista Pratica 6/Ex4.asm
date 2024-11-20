.text
.globl start

#x >> s0
#y >> s1

start:
	ori $s0, $zero, 3	#x=3
	ori $s1, $zero, 4	#y=4
	
	sll $t0, $s0, 4		#t0=16x
	sub $t1, $t0, $s0	#t1=t0-x(15x)
	
	sll $t2, $s1, 6		#t2 = 64y
	sll $t3, $s1, 1		#t3 = 2y
	add $t3, $t3, $s1	#t3 = 3y
	add $t3, $t3, $t2	#t3 = 67y
	
	add $t4, $t1, $t3	#t4=15x+67y
	sll $s2, $t4, 2 	#z=(15x+67y)*4
	
#fim	