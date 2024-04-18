/*
	Exercicio 2 Guia 5
	Nome: Tulio Gomes Braga
	Matricula: 802512
*/


module nnand(output ss, input aa, input bb);
	assign ss = ~(aa&bb);
endmodule

module onlyNand(output s, input a, input b);	// (~a|b) >> ~(~(~a|b)) >> ~(a&~b) >>  a nand ~b >> vou utilizar a propria nand pra negar o b
	wire not_b;
	nnand nb (not_b, b, b);
	nnand saida (s, a, not_b);
endmodule


module Guia_0502;

	reg x,y;
	wire s_s;
	onlyNand teste(.s(s_s), .a(x), .b(y));
	
	initial begin : main
		x=1;
		y=0;

		#10;
		$display("Resultado: %b", s_s);
	
	end
endmodule 


/*
Saida:
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0502.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Resultado: 0
*/
