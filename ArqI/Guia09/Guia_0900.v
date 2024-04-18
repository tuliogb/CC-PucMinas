/*
	Nome: Tulio Gomes Braga 
	Matricula: 802512	
*/


module clock ( output clk );
	reg clk;		// Envio do sinal
	
	initial begin
		clk = 1'b0;	// Inicio do clock com valor 0;
	end
	
	always begin
		#12 clk = ~clk;	
	end

endmodule 
module Guia_0900;
	wire clk;		// Saida da caixa geradora
	clock CLK1 ( clk );
	initial begin
		$dumpfile ( "Guia_0900.vcd" );		// Especifica o local de despejo dos dados >> "formas de onda"
		$dumpvars;				// Especifica quais sinais devem ser incluidos no despejo "dumpvars 0 clk reset data_out;"
		#120 $finish;				// Espera 120 unidades de tempo e encerra a execução >> o module clock é infinito por causa do always
	end
endmodule
