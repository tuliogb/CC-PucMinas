.data
.text
.globl start

#x -> s0
#y -> s1
#z -> s2

	start: 
		ori $s0, $0, 0x186A	#s0 = 0x186A
		ori $s1, $0, 0x1388	#s1 = 0x1388
		ori $s2, $0, 0x61A8	#s2 = 0x61A8
	
		mult $s0,$s1		#s0 * s1
		mflo $t0		#t0 = s0 * s1
		div $t0, $s2 		#s3 = t0 / s2
		mflo $t1
		sll $s3, $t1, 8 	#s3 << 8