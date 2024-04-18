/*
Guia_0201.v
Nome: Tulio Gomes Braga
Matricula: 802512

Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga

module Guia_0201;

	real x = 0 ; 
	real power2 = 1.0; 						// Numero de ponto flutuante (Iniciado como 2^0)
	integer y = 7 ; 						// Contador do laço
	reg [7:0] b = 8'b10100000; 				// binary (only fraction part, Big Endian)

	initial begin : main
		$display ( "Guia_0201 - Tests" );
		$display ( "x = %f" , x );			
 		$display ( "b = 0.%8b", b );
 
 		while ( y >= 0 ) begin				// Como é >= 7 sao 8 loops

 			power2 = power2 / 2.0;			// Esta repartindo a cada laco.

 			if ( b[y] == 1 ) begin
 				x = x + power2;				// Esta repartindo de acordo com a ocorrencia de 1 no numero b.
 			end
 
 			$display ( "x = %f", x );
 			y=y-1;

 		end // end while
 	end // main
endmodule 							// O codigo faz a passagem de binario(negativo) pra decimal 

Saida do Exemplo
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0201.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
Guia_0201 - Tests
x = 0.000000
b = 0.10100000
x = 0.500000
x = 0.500000
x = 0.625000
x = 0.625000
x = 0.625000
x = 0.625000
x = 0.625000
x = 0.625000

*/



