.text
.globl start

#x >> s0
#y >> s1
#z >> s2

start:
	ori $t0, $zero, 0x186A		#t0=0x186A
	sll $s0, $t0, 4			#x=0x186A0 (100000)
	ori $t1, $zero, 0x30D4		#t1=0x30D4
	sll $s1, $t1, 4			#y=0x30D40 (200000)
	add $s2, $s0, $s1		#z=x+y
	
#fim	