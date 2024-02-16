/*
Guia_0205.v
Nome: Tulio Gomes Braga
Matricula: 802512


Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga

module Guia_0205;

	reg [7:0] a = 8'b000_1010 ; 
	reg [7:0] b = 8'b000_1100 ; 
	reg [7:0] c;

	initial begin : main
		$display ( "Guia_0205 - Tests" );
		$display ( "a = %8b", a );
		$display ( "b = %8b", b );		// Vai operando b e a, atribuindo os valores a c.
		
		c = a+b;
		$display ( "c = a+b = %8b", c );
		
		c = a-b;
		$display ( "c = a-b = %8b", c );
		
		c = b-a;
		$display ( "c = b-a = %8b", c );
		
		c = a*b;
		$display ( "c = a*b = %8b", c );
		
		c = b/a;
		$display ( "c = b/a = %8b", c );
		
		c = b%a;
		$display ( "c = b%%a = %8b", c );
	end 
endmodule 


Saida do Exemplo
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0205.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Guia_0205 - Tests
a = 00001010
b = 00001100
c = a+b = 00010110
c = a-b = 11111110
c = b-a = 00000010
c = a*b = 01111000
c = b/a = 00000001
c = b%a = 00000010
*/




module Guia_0205;

	reg[4:0] l= 4'b1101; // 13
	reg[0:4] b= 4'b1101; // 13 		// So altera a forma com que sao endereçados mas ambos sao 13.
	
	reg[9:0] r= 0;

	initial begin : main
		r = l*b;
		$display("%d * %d = %b(%d)", l, b, r,r);
		
	end
endmodule


/*
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0205.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
13 * 13 = 0010101001( 169)
*/	










































