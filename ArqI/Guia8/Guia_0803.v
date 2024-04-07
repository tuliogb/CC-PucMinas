/*
	Nome: Tulio Gomes Braga
	Matricula: 802512
*/

// TENDO 1 COMO VERDADEIRO E 0 COMO FALSO

module Guia_0803;
	
	reg[5:0] x,y;
	reg[5:0] saida;
	integer i;

	initial begin : main 
		x=6'b000101;
		y=6'b000001;

		for (i=0; i<6; i=i+1) begin
			if(x[i]==y[i]) saida[i]=1;
			else saida[i]=0;
		end
		
		#10;
		$display("Resultado = %b", saida); 
	end

endmodule
