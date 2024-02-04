/*
Guia_0105.v
Nome: Tulio Gomes Braga
Matricula: 802512

Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga

module Guia_0105;

	integer x = 13; 
	reg [7:0] b = 0; 
	reg [0:2][7:0] s = "PUC"; 								

	 "Matriz" de tamanho 3x8:
	s[0] | bit7 bit6 bit5 bit4 bit3 bit2 bit1 bit0
 	s[1] | bit7 bit6 bit5 bit4 bit3 bit2 bit1 bit0
 	s[2] | bit7 bit6 bit5 bit4 bit3 bit2 bit1 bit0
	

	initial begin : main
 		$display ( "Guia_0105 - Tests" );
		$display ( "x = %d" , x );
 		$display ( "b = %8b", b );
 		$display ( "s = %s" , s );
 		b = x;
 		$display ( "b = [%4b] [%4b] = %h %h", b[7:4], b[3:0], b[7:4], b[3:0] );
		s[0] = "-";
 		s[1] = 8'b01001101; // 'M'		
 		s[2] = 71; // 'G'
 		$display ( "s = %s" , s );
	end 
endmodule 


Saida do Exemplo
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0105.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
Guia_0105 - Tests
x =          13
b = xxxxxxxx
s = PUC
b = [0000] [1101] = 0 d
s = -MG
*/

module Guia_0105;
	reg [0:4][7:0] s = "TULIO"; 								

	initial begin : main
 		$display ( "s = %s" , s );
 		$display("s[1] = %s", s[0]);
		$display("s[2] = %s", s[1]);
		$display("s[3] = %s", s[2]);
		$display("s[4] = %s", s[3]);
		$display("s[5] = %s", s[4]);

		s[0] = 45; //'-'
 		s[1] = 8'b01001101; // 'M'		
 		s[2] = 71; // 'G'
 		$display ( "s = %s" , s );
	end 
endmodule 

/*
PS C:\Users\Túlio\Desktop\Projetos> iverilog -o guia .\Guia_0105.v
PS C:\Users\Túlio\Desktop\Projetos> vvp .\guia
s = TULIO
s[1] = T
s[2] = U
s[3] = L
s[4] = I
s[5] = O
s = -MGIO	// Saiu com o resto de TULIO que ficou guardado.
*/