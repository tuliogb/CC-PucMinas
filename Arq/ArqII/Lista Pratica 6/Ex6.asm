.text
.globl start

#x >> s0
#y >> s1
#z >> s2

start:
	ori $t0, $zero, 0x7FFF		#t0=0x7FFF
	sll $t0, $t0, 16		#t0=0x7FFF0000	(empurra pra parte alta)
	ori $s0, $t0, 0xFFFF		#x=0x7FFFFFFF
	
	ori $t1, $zero, 0x493E		#t1=0x493E
	sll $s1, $t1, 4			#y=0x493E0 
	sll $t2, $t1, 2			#t2=0x124f80 (4y)
	
	sub $s2, $s0, $t2		#z=x-t2(x-4y)
	
#fim	