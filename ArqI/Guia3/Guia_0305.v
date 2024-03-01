/*
	Guia_0305.v
	Tulio Gomes Braga
	802512
*/

module Guia_0305;
	reg [2:0] a = 0 ; // 0 a 7
 	reg [3:0] b = 0 ; // 0 a 15
 	reg [4:0] c = 0 ; // binary
 	reg [4:0] d = 0 ; // binary
 	reg [6:0] e = 0 ; // binary

 	initial begin : main
		a = 5 + 3; 					// OVERFLOW (transbordamento) '1'[000]
		b = 10 - 5 + 25 + 3 + 1; 	// OVERFLOW (transbordamento)  
		c = 2 - 35; 				// OVERFLOW (transbordamento)

		$display("\nOverflow");
		$display("a = %d = %3b = %d", (5+3) , a, a);
		$display("b = %d = %4b = %d", (10 - 5 + 25 + 3 + 1), b, b);						//Porque so mostrou 4bits?
		$display("c = %d = %5b = %d", (2-35), c, c);

		$display("\nComplements");
		$display("0= %d = %3b = %3b", ~1 , (1-1), ~(1*1) );
		$display("1= %d = %3b = %3b", ~0 , (2-1), ~(0*1) );
		$display("2= %d = %3b = %3b", (1+1), (3-1), ~0+~0 );
	end 
endmodule 

/*
Saida:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> iverilog -o guia .\Guia_0305.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> vvp .\guia

Overflow
a =           8 = 000 = 0
b =          34 = 0010 =  2
c =         -33 = 11111 = 31

Complements
0=          -2 = 00000000000000000000000000000000 = 11111111111111111111111111111110
1=          -1 = 00000000000000000000000000000001 = 11111111111111111111111111111111
2=           2 = 00000000000000000000000000000010 = 11111111111111111111111111111110
*/