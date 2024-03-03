/*
Nome: Tulio Gomes Braga
Matricula: 802512
*/

module b(output s1, output s2, input x,y,z);
	assign s1 = (~x+y) | (x&y);
	assign s2 = (~x+y);
endmodule

module c(output s1, output s2, input x,y,z);
	assign s1 = ~(~x&y) & (x|~y);
	assign s2 = (x|~y);
endmodule


module Guia_0402;
	
	reg x,y,z;
	wire s_b, s_bb, s_c, s_cc;	

	b funcao2 (.s1(s_b), .s2(s_bb), .x(x), .y(y), .z(z));
	c funcao3 (.s1(s_c), .s2(s_cc), .x(x), .y(y), .z(z));

	initial begin : main
		x=1;
		y=1;
		z=0;

		#10;
		$display("B: Normal %b, Simplificada: %b", s_b, s_bb);
		$display("C: Normal %b, Simplificada: %b", s_c, s_cc);
	end
endmodule

/*
Saida do Teste:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas>iverilog -o guia .\Guia_0402.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas>vvp .\guia
B: Normal 1, Simplificada: 1
C: Normal 1, Simplificada: 1
*/