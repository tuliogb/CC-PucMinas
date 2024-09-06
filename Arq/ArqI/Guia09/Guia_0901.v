/*
	Nome: Tulio Gomes Braga 
	Matricula: 802512	
*/

module clock ( output clk );
	reg clk;
	
	initial begin 
		clk = 1'b0;
	end 
	
	always begin
		#12 clk = ~clk;
	end
endmodule

module pulse ( signal, clock );
	input clock;
	output signal;
	reg signal;
	
	always @ ( clock ) begin	
		signal = 1'b1;
		#3 signal = 1'b0;
		#3 signal = 1'b1;
		#3 signal = 1'b0;
	end
endmodule

module trigger ( signal, on, clock );
	input on, clock;
	output signal;
	reg signal;
	
	always @ ( posedge clock & on ) begin		// Sempre que o clock e o input on estiverem ativos sera executado o codigo
		#60 signal = 1'b1;
		#60 signal = 1'b0;
	end
endmodule 	



module Guia_0901;
	wire clock;
	clock clk ( clock );
	
	reg p;
	wire p1,t1;
	
	pulse pulse1 ( p1, clock );
	trigger trigger1 ( t1, p, clock );		// P é um controlador pra sair ou nao sair o trigger
	
	initial begin
		p = 1'b0;	
	end
	
	initial begin
		$dumpfile ( "Guia_0901.vcd" );
		$dumpvars ( 1, clock, p1, p, t1 );
		
		
		#060 p = 1'b1; 
		#120 p = 1'b0;
		#180 p = 1'b1;
		#240 p = 1'b0;
		#300 p = 1'b1;
		#360 p = 1'b0;
		#376 $finish;
	end
endmodule

/*
Podemos ver que o pulso p é inicializado em 0, aguarda 60s, e passar pra 1 durante 120s. No tempo de 60s a linha 37 do trigger ja comeca a ser executado mas a saida é barrada porque nao foi inicializado valor. Quando a condicao é valida, ou seja, o clock esta em alta ele da um pulso em 0 por 60s e um pulso em 1 por 60s.
*/



