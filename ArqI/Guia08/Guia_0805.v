/*
	Nome: Tulio Gomes Braga
	Matricula: 802512
*/


module Guia_0805;
	
	reg[5:0] x=6'b000101;
	reg[5:0] y=6'b000001;


	initial begin : main 
		
		#10;
		$display("Resultado = %b", ~x+y); 
	end

endmodule
