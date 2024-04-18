/*
Guia_0103.v
Nome: Tulio Gomes Braga
Matricula: 802512

Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga

module Guia_0103;

	integer x = 13; 
	reg [7:0] b = 0;

	initial	begin : main
 		$display ( "Guia_0103 - Tests" );	
		$display ( "x = %d" , x );
 		$display ( "b = %8b", b );
 		b = x;
 		$display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );			// Exibe o numero 13 em binario, em octal, em hexa minusculo e hexa maiusculo.
 	end 
endmodule 


Saida do Exemplo
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0103.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
Guia_0103 - Tests
x =          13
b = 00000000
b = 00001101 (2) = 015 (8) = 0d (16) = 0D (16)

*/

module Guia_0103;

	integer x = 20;
	reg [7:0] b = 10;
	integer resultado = 0;
	integer divisao = 0;

	initial begin : main
		resultado = x + b;
		$display ( "b = %B (2) === %o (8) === %x (16) === %X (16)", resultado, resultado, resultado, resultado );

		//divisao = resultado / 2;
		//$display("Resultado da divisao: %f", divisao);	
	end
endmodule

/*
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0103.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
b = 00000000000000000000000000011110 (2) === 00000000036 (8) === 0000001e (16) === 0000001e (16)		// Sai com essa quantidade de 0 porque declarei resultado como inteiro decimal que tem 32 bites por padrao verilog


Se colocar como binario de 8bites (reg [7:0] resultado = 0;) a saida fica:
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0103.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
b = 00011110 (2) === 036 (8) === 1e (16) === 1e (16)
*/