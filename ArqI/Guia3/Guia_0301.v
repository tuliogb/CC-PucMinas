/*
	Guia_0301.v
	Tulio Gomes Braga
	802512
*/

module Guia_0301;

	reg [7:0] a = 8'b000_1010 ; 
 	reg [6:0] b = 8'b000_101 ; 
 	reg [5:0] c = 8'b001_01 ; 

 	reg [7:0] d = 0 ; 
 	reg [6:0] e = 0 ; 
 	reg [5:0] f = 0 ; 

	initial begin : main

 		d = ~a+1;	//1010-> 0101+1 -> 0110		
 		$display ( "a = %8b -> C1(a) = %8b -> C2(a) = %8b", a, ~a, d );			

 		e = ~b+1;
 		$display ( "b = %7b -> C1(b) = %7b -> C2(b) = %7b", b, ~b, e );

 		f = ~c+1;
 		$display ( "c = %6b -> C1(c) = %6b -> C2(c) = %6b", c, ~c, f );

 	end 

endmodule 


/*
O codigo pela um registrador e faz a passagem dos valores para negativo atraves de C1 e C2
C1 = inversao
C2 = inversao + 1 (d,e,f)

Saida:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> iverilog -o guia .\Guia_0301.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> vvp .\guia
a = 00001010 -> C1(a) = 11110101 -> C2(a) = 11110110
b = 0000101 -> C1(b) = 1111010 -> C2(b) = 1111011				
c = 000101 -> C1(c) = 111010 -> C2(c) = 111011
*/