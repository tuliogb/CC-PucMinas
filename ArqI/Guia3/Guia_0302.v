/*
	Guia_0302.v
	Tulio Gomes Braga
	802512
*/
module Guia_0302;

 	reg [8:0] a = 8'hb8 ; // hexadecimal
 	reg [9:0] b = 8'o154 ; // octal
 	reg [5:0] c = 13 ; // decimal

 	reg [7:0] d = 0 ; 
 	reg [6:0] e = 0 ; 
 	reg [5:0] f = 0 ; 

	initial begin : main
		d = ~a+1;
		$display ( "a = %8b -> C1(a) = %8b -> C2(a) = %8b", a, ~a, d );

		e = ~b+1;
		$display ( "b = %7b -> C1(b) = %7b -> C2(b) = %7b", b, ~b, e );

		f = ~c+1;
		$display ( "c = %6b -> C1(c) = %6b -> C2(c) = %6b", c, ~c, f );
	end 
endmodule 

/*
Saida:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> iverilog -o guia .\Guia_0302.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> vvp .\guia
a = 010111000 -> C1(a) = 101000111 -> C2(a) = 01001000
b = 0001101100 -> C1(b) = 1110010011 -> C2(b) = 0010100
c = 001101 -> C1(c) = 110010 -> C2(c) = 110011
*/