/*
Nome: Tulio Gomes Braga
Matricula: 802512
*/

module a (output s, input x, y, z);
	assign s = x & ~(y | ~z);						//atribui a variavel de saida s o valor booleano da expressao
endmodule 

module e (output s, input x, y, z);
	assign s = (x | y) & ~(~y | z);						//atribui a variavel de saida s o valor booleano da expressao
endmodule 


module Guia_0401;
	
	reg x,y,z;										// reg porque é o mesmo de binario, 0 e 1.
	wire s_a, s_e;											// wire de fio, as saidas sao barramentos.

	a funcao1(.s(s_a), .x(x), .y(y), .z(z));		// nome do modulo, nome da funcao, portas de conexao 
	e funcao5(.s(s_e), .x(x), .y(y), .z(z));

	initial begin : main
		x=0;
		y=1;
		z=0;

		#10;
		$display("Resultado de a: %b", s_a);
		$display("Resultado de e: %b", s_e);
	end
endmodule


/*
Saida do Teste:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas>iverilog -o guia .\Guia_0401.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas>vvp .\guia
Resultado de a: 0
Resultado de e: 1
*/