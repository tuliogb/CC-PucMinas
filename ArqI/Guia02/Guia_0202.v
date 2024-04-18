/*
Guia_0202.v
Nome: Tulio Gomes Braga
Matricula: 802512

Codigo Exemplo fornecido por Theldo Cruz e comentado por Tulio Braga


module Guia_0202;

	real x = 0.75; 
	integer y = 7 ; 
	reg [7:0] b = 0 ; 

	initial begin : main
		$display ( "Guia_0202 - Tests" );
		$display ( "x = %f" , x );
		$display ( "b = 0.%8b", b );
		
		while ( x > 0 && y >= 0 ) begin				// Confere se o numero nao zerou e garante o laço dos 8bits
			if ( x*2 >= 1 ) begin				// Se entrar é porque porque alcançou uma potencia
				b[y] = 1;
				x = x*2.0 - 1.0;			// Por isso tiramos o 1 para sinalizar no binario e continuamos
			end 
			else begin					// Caso nao entre continuamos a dobrar
				b[y] = 0;
				x = x*2.0;
			end 

			$display ( "b = 0.%8b", b );			// Vai mostrando o resultado de cada laço
			y=y-1;						// Vai descrencendo a posicao dos bits (little endian)
		end 
	end 
endmodule


Saida do Exemplo
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog  -o guia Guia_0202.v
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Guia_0202 - Tests
x = 0.750000
b = 0.00000000
b = 0.10000000			// Esse é o 1 do resuldado 75*2
b = 0.11000000			// Esse é o 1 do resultado 50*2

*/
















