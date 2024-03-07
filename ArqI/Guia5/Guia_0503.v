/*
	Exercicio 3 Guia 5
	Nome: Tulio Gomes Braga
	Matricula: 802512
*/


module nnand(output ss, input aa, input bb);
	assign ss = ~(aa|bb);
endmodule

module onlyNor(output s, input a, input b);	// faz a exepressao normalmente depois nega o resultado (~a|b)
	wire not_a;
	nnand um (not_a, a, a);
	
	wire resp;
	nnand dois (resp, not_a, b);
	
	nnand saida (s, resp, resp);
endmodule


module Guia_0503;

	reg x,y;
	wire s_s;
	onlyNor teste(.s(s_s), .a(x), .b(y));
	
	initial begin : main
		x=1;
		y=0;

		#10;
		$display("Resultado: %b", s_s);
	
	end
endmodule 


/*
Saida:
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0503.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia
Resultado: 0
*/
