/*
	Exercicio 1 Guia 5
	Nome: Tulio Gomes Braga
	Matricula: 802512


module exemplo1(output s, input a, input b);	   // (~a&b) >> ~(~(~a&b) >> ~(a|~b) = a nor ~b
	wire not_a;
	not not1(not_a,a);			 // Modulo(Padrao)	Instancia	(Receptar a negacao, oque vai ser negado)
	and and1(s,not_a,b);			// Modulo(Padrao)	Instancia	Portas de Conexao  	
endmodule


module Guia_0105;
	
	reg x,y;
	wire s_ex1;
	
	exemplo1 teste(.s(s_ex1), .a(x), .b(y));
	
	initial begin : main 
		x=0;
		y=1;

		#10;
		$display("Resultado de ex1: %b", s_ex1);	
	end
endmodule

Saida: 	tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0501.v 
	tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia
	Resultado de ex1: 1

*/

module nnor(output ss, input aa, input bb);
	assign ss = ~(aa|bb);
endmodule

module onlyNor(output s, input a, input b);
	wire not_b;		//	0
	nnor norb(not_b,b,b);
	nnor norab(s,a,not_b);
endmodule


module Guia_0501;

	reg x,y;
	wire s_final;
	onlyNor teste(.s(s_final), .a(x), .b(y));
	
	initial begin : main 
		x=0;
		y=1;
		
		#10;
		$display("Resultado de ex1: %b", s_final);	
	end
endmodule






