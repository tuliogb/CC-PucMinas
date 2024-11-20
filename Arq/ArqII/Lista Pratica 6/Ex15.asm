.data
.text
.globl start

#t0 -> first address
#t1 -> offset
#v[i] -> $t4
#soma -> s1
#i -> $t2   (0)
#tam -> $t3 (100)

	start: 
		ori $t0, $0, 0x1001	#t0 = 0x1001
		sll $t0, $t0, 16	#t0 = 0x10010000
		or $t1, $t0, $0		#t1 = t0
		or $t2, $0, $0		#i = 0
		ori $t3, $0, 100	#tam = 100
	
	if:	
		beq $t2, $t3, end	#if(t2==t3){ goTo end; }
		add $t4, $t2, $t2 	#t4 = 2*1 
		addi $t4, $t4, 1	#t4 = 2*i + 1 
		add $s0, $s0, $t4	#soma = soma + t4
		sw $t4, 0($t1)		#men[$t1] = t4
		addi $t1, $t1, 4	#t1 = t1 + 4 
		addi $t2, $t2, 1	#i++
		j if			#goto if
		
	end:	
		sw $s0, 0($t1)		#men[$t1] = s0