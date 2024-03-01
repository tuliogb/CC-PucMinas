/*
	Guia_0303.v
	Tulio Gomes Braga
	802512
*/

module Guia_0303;

 	reg signed [7:0] a = 8'b1111_1010; 
	reg signed [7:0] d = 0 ; 
 	reg signed [6:0] e = 0 ; 

 	initial begin : main
		 d = ~a+1;		// Fazendo o complemento de 2	
		 e = ~(a-1);	// 1111 1010 -> 1111 1001 -> 0000 0110 -> justamente o 6 que faltada pro 10 chegar em 16.
		 $display ( "a = %8b -> C1(a) = %8b -> C2(a) = %8b = %d", a, ~a, d, e );
 	end 
endmodule 
