.text
.globl start


start:
	ori $8,$0,0x01
	#vamosFormarF
	sll $8,$8,31
	sra $8,$8,31
#fim