/*
Guia_0204.v
Nome: Tulio Gomes Braga
Matricula: 802512


Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga

module Guia_0204;

	real x = 0.625;
	reg [7:0] b = 8'b1010_0000 ; 
	integer q [3:0];				// Um vetor de 4 posiçoes onde cada posicao pode guardar 32bits porque é inteiro

	initial begin : main
		$display ( "Guia_0204 - Tests" );
		$display ( "x = %f" , x );
		$display ( "b = 0.%8b", b );
		$display ( "b = 0.%x%x (16)", b[7:4],b[3:0] );
		
		q[3] = b[7:6];
		q[2] = b[5:4];						
		q[1] = b[3:2];
		q[0] = b[1:0];
		
		$display ( "b = 0.%2b %2b %2b %2b (2)", b[7:6],b[5:4],b[3:2],b[1:0] );
		$display ( "q = 0.%2d %2d %2d %2d (4)", q[3] ,q[2] ,q[1] ,q[0] );      		// imprime o valor de cada repartimento com pelo menos 2 casas 
	end 
endmodule 


Saida do Exemplo
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog  -o guia Guia_0204.v
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Guia_0204 - Tests
x = 0.625000
b = 0.10100000
b = 0.a0 (16)
b = 0.10 10 00 00 (2)
q = 0. 2  2  0  0 (4)
*/

module Guia_0204;

	integer q [3:0];

	initial begin : main
	    q[0] = 32'b10101010101010101010101010101010;
	    q[1] = 32'b11001100110011001100110011001100;
	    q[2] = 32'b11110000111100001111000011110000;
	    q[3] = 32'b00001111000011110000111100001111;
	    
	    $display ( "q = %d / %d / %d / %d", q[3] ,q[2] ,q[1] ,q[0] );
	end
	
	
endmodule

/* 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog  -o guia Guia_0204.v
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
q =  252645135 / -252645136 / -858993460 / -1431655766
*/




