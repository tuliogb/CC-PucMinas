/*
	Guia_0304.v
	Tulio Gomes Braga
	802512
*/

module Guia_0304;

 	reg signed [7:0] a = 8'b1000_1010; // Signed significa que contem o bit do sinal
 	reg signed [7:0] b = 8'b0000_1010; 

 	reg signed [7:0] c = 0 ;
 	reg signed [7:0] d = 0 ; 

 	initial begin : main

		$display ( "a = %8b = %d", a, a );
		$display ( "b = %8b = %d", b, b );

		c=~b+1;
		d = a-c;

		$display ( "d=a-(c2)b = %8b-%8b = %8b = %d", a, c, d, d );
	end 
endmodule 

/*
Saida:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> iverilog -o guia .\Guia_0304.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> vvp .\guia
a = 10001010 = -118
b = 00001010 =   10
d=a-(c2)b = 10001010-11110110 = 10010100 = -108
*/