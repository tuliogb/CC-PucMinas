/*
Guia_0203.v
Nome: Tulio Gomes Braga
Matricula: 802512

Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga


module Guia_0203;
	real x = 0.625;
	reg [7:0] b = 8'b1010_0000 ; 


	initial begin : main
		$display ( "Guia_0203 - Tests" );
		$display ( "x = %f" , x );
		$display ( "b = 0.%8b", b );
		
		$display ( "b = 0.%x%x (16)", b[7:4],b[3:0] );				// Apresenta em x(hexa) os 4bits + 4bits
		$display ( "b = 0.%o%o%o (8) ", b[7:6],b[5:3],b[2:0] );			// Apresenta em o(octal) os 3bits + 3 bits + 3 bits
	end 
endmodule 


Saida do Exemplo
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog  -o guia Guia_0203.v
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Guia_0203 - Tests
x = 0.625000
b = 0.10100000
b = 0.a0 (16)
b = 0.240 (8) 

*/

module Guia_0203;
	real x = 31;
	reg [5:0] b = 0;
	
	initial begin : main
		b=x;
		$display ("%b", b[5:4]);
		$display ("%b", b[3:2]);
		$display ("%b", b[1:0]);
		
		$display ("Numero acima: %d", b);
	end
endmodule

/*
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog  -o guia Guia_0203.v
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
01
11
11
Numero acima: 31

*/
