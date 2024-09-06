/*
Guia_0104.v
Nome: Tulio Gomes Braga
Matricula: 802512

Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga

module Guia_0104;
	integer x = 13;
	reg [7:0] b = 0; 

	initial	begin : main
 		$display ( "Guia_0104 - Tests" );
 		$display ( "x = %d" , x );
 		$display ( "b = %8b", b );
 		b = x;
 		$display ( "b = [%4b] [%4b] = %x %x", b[7:4], b[3:0], b[7:4], b[3:0] );  //	00001101 = 13 , coloca os 4 primeiros bits separado dos outros 4. Apresenta tambem em hexadecimal.
 	end 
endmodule 


Saida do Exemplo
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0104.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
Guia_0104 - Tests
x =          13
b = 00000000
b = [0000] [1101] = 0 d
*/


module Guia_0104;
	integer x = 1000;
	reg [9:0] b = 0; 

	initial	begin : main
 		$display ( "Numero = %d" , x );
 		b = x;
 		$display ( "b = [%5b] [%5b] ", b[9:5], b[4:0] ); 
 	end 
endmodule 


/*
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0104.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
x =        1000
b = [11111] [01000]
*/