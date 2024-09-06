/*
	Exercicio 8 Guia 5
	Nome: Tulio Gomes Braga
	Matricula: 802512
*/


module nnand(output ss, input aa, input bb);
	assign ss = ~(aa&bb);
endmodule

module onlyNand(output s, input a, input b);	// ~(a&~b) >> a nand ~b 
	wire not_a;
	wire not_b;
	
	nnand um (not_a, a, a);
	nnand dois (not_b, b, b);
	
	nnand tres (s, not_a, not_b);
endmodule


module Guia_0508;

	reg x,y;
	wire s_s;
	onlyNand teste(.s(s_s), .a(x), .b(y)); 	
	
	initial begin : main
		x=0;
		y=0;
		#10;
		$display("Resultado 00: %b", s_s);

		x=0;
		y=1;
		#10;
		$display("Resultado 01: %b", s_s);

		x=1;
		y=0;
		#10;
		$display("Resultado 10: %b", s_s);

		x=1;
		y=1;
		#10;
		$display("Resultado 11: %b", s_s);
	
	end
endmodule 


/*
Saida:
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Resultado 00: 0
Resultado 01: 1
Resultado 10: 1
Resultado 11: 1
*/
