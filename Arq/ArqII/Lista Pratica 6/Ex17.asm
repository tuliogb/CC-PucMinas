.text 
.globl start

#x >> $s0
#y >> $s1
#s2 >> enderecoBase

		
	start: 	
		ori $s2,$zero,0x1001	#temp=1001;
		sll $s2,$s2,16		#temp=temp<<4
		
		lw $s0,0($s2)		#x=men[0];
		lw $s1,4($s2)		#y=men[1];
		
		or $t0,$zero,$s1	#temp0=y;
		ori $t1, $zero,0    	#temp1=0
		
	while:
		beq $t0,$zero,fim	#y==0? fim
		add $t1,$t1,$s0		#temp1=temp1+x
		addi $t0,$t0,-1		#temp0=temp0-1
		j while			#goTo while
	
	fim:	sw $t1,8($s2)		#men[2]=temp1(x*y)
		
#fim