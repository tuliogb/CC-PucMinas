/*
Guia_0101.v
Nome: Tulio Gomes Braga
Matricula: 802512

Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga

module Guia_0101;										//	Declara o inicio do modulo 

	 integer x = 13; 									// Variavel x iniciada com o valor 13.
	 reg [7:0] b = 0; 									// Registrador de 8bits chamado b, foi iniciado com o valor 0.

	 initial begin : main								// Inicia o bloco main.
		 $display ( "Guia_0101 - Tests" );				// Exibe uma mensagem na saida padrao.
		 $display ( "x = %d" , x );						// Valor de x em decimal.
		 $display ( "b = %8b", b );						// Valor de b em binario.
		 b = x;
		 $display ( "b = %8b", b );						// Mostra o valor de x (0) em binario.
	 end 

endmodule 												// Finaliza o modulo.


Saida do Exemplo
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0101.v
PS C:\Users\Túlio\Desktop\Projetos> vvp guia
Guia_0101 - Tests
x =          13
b = 00000000
b = 00001101

*/


// Estudo do exemplo
module Guia_0101;
	
	integer x = 10;
	integer y = 20;
	integer resultado;

	reg [0:4] a = 31;      // Esse registrador esta no maximo, porque 32 é [0:5]
	reg [0:5] b = 32;

	initial begin : main
		resultado = x + y;
		$display ("Soma do x + y: %d", resultado);

		$display ( "a = %8b", a );	
		$display ( "b = %8b", b );	
	end
endmodule

/*
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0101.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
Soma do x + y:          30
a =    11111
b =   100000
*/