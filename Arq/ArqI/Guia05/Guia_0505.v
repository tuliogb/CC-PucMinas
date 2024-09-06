/*
	Exercicio 5 Guia 5
	Nome: Tulio Gomes Braga
	Matricula: 802512
*/


module nnor(output ss, input aa, input bb);
	assign ss = ~(aa|bb);
endmodule

module onlyNor(output s, input a, input b);	 
	wire not_a;
	wire not_b;
	
	wire and1;
	wire and2;
	
	nnor um (not_a, a, a);
	nnor dois (not_b, b, b);
	
	nnor tres (and1, a, not_b);
	nnor quatro (and2, not_a, b);
	
	nnor cinco (s, and1, and2);
endmodule


module Guia_0505;

	reg x,y;
	wire s_s;
	onlyNor teste(.s(s_s), .a(x), .b(y));
	
	initial begin : main
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
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0505.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Resultado 10: 0
Resultado 11: 1
*/









