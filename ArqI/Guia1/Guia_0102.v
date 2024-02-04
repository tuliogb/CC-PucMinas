/*
Guia_0102.v
Nome: Tulio Gomes Braga
Matricula: 802512

Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga

module Guia_0102;

	integer x = 0; 
	reg [7:0] b = 8'b0001101; 		// 8'b é usado pra indicar que o valor seguinte é um binario.

	initial begin : main
 		$display ( "Guia_0102 - Tests" );
 		$display ( "x = %d" , x );
 		$display ( "b = %8b", b );
 		x = b;							// Atribui o 13 em binario ao x.
 		$display ( "b = %d", x );	
	end 

endmodule 

Saida do Exemplo
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0102.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
Guia_0102 - Tests
x =           0
b = 00001101
b =          13

*/


module Guia_0102;

	integer resultado = 0; 
	reg [7:0] a = 8'b0001101; 	
	reg [7:0] b = 8'b0000010; 

	initial begin : main
 		$display ( "Guia_0102 - Tests" );
 		$display ( "a = %8b", a );
 		$display ( "b = %8b", b );
 		resultado = a + b;						// Somando em binario (1111)
 		$display ( "Soma: = %d", resultado); 
	end 

endmodule 

*/
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0102.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
Guia_0102 - Tests
a = 00001101
b = 00000010
Soma: =          15
*/


