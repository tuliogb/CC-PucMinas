.text
.globl start


#a >> s1
#b >> s2
#c >> s3
#d >> s4
#x >> s5
#y >> s6


start:
	addi $s1,$zero,2	#a=2
	addi $s2,$zero,3	#b=3
	addi $s3,$zero,4	#c=4
	addi $s4,$zero,5	#d=5
	
	add $t0,$s1,$s2		#temp0=a+b
	add $t1,$s3,$s4		#temp1=c+d
	sub $s5,$t0,$t1		#y=temp0-temp1
	
	sub $t0,$s1,$s2		#temp0=a-b
	add $s6,$t0,$s5		#y=temp0+x
	sub $s2,$s5,$s6		#b=x-y
	
	
#fim