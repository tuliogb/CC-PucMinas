.text
.globl start

#x >> s0
#y >> s1

start:
	ori $s0, $zero, 3	#x=3
	ori $s1, $zero, 4	#y=4
	
	add $t0, $s0, $s0	#t0=2x
	add $t1, $t0, $t0	#t1=4x
	add $t2, $t1, $t1	#t2=8x
	add $t3, $t2, $t2	#t3=16x
	
	sub $s2, $t3, $s0	#z=15x
	
	add $t0, $s1, $s1	#t0=2y
	add $t1, $t0, $t0	#t1=4y
	add $t2, $t1, $t1	#t2=8y
	add $t3, $t2, $t2	#t3=16y
	add $t4, $t3, $t3	#t4=32y
	add $t5, $t4, $t4	#t5=64y
	add $t6, $t0, $s1	#t6=3y
	add $t7, $t5, $t6	#t7=67y
	
	add $s2, $s2, $t7	#z=15x+67y
	add $t0, $s2, $s2	#t0=2*z
	add $s2, $t0, $t0	#z=4*t0
	
#fim	