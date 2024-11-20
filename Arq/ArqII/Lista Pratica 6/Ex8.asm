.text
.globl start


start:
	ori $8,$0,0x1234
	sll $8,$8,16
	ori $8,$8,0x5678
	
	sra $9,$8,24		#12
	
	sll $10,$8,8
	srl $10,$10,24		#34
	
	sll $11,$8,16
	srl $11,$11,24		#56
	
	sll $12,$8,24
	srl $12,$12,24		#78

#fim