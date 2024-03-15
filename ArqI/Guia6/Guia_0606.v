/*
	Nome: Tulio Gomes Braga
	Matricula: 802512
*/

module normal(output s1, input x, input y, input w, input z);
    assign s1 = (~(~y|w|~x) & ~(y&~w&x)) | ~((y&w&z)|~x);
endmodule

module simplificada(output s2, input x, input y, input w, input z);
    assign s2 = x|y & x|~y & ~y|~w|~z;
endmodule



module Guia_0605;

	reg x, y, w, z;
	wire s1, s2;
	
	normal n (s1, x,y,w,z);
    simplificada s (s2,x,y,w,z);

	initial	begin : main

    	$display("   x    y    w    z    s1   s2");

		$monitor("%4b %4b %4b %4b %4b %4b", x, y, w, z, s1, s2);

		x = 1'b0; y = 1'b0; w = 1'b0; z = 1'b0; 
		#1

		x = 1'b0; y = 1'b0; w = 1'b0; z = 1'b1;
		#1

    	x = 1'b0; y = 1'b0; w = 1'b1; z = 1'b0;
		#1

    	x = 1'b0; y = 1'b0; w = 1'b1; z = 1'b1;
		#1

   		x = 1'b0; y = 1'b1; w = 1'b0; z = 1'b0;
		#1

   		x = 1'b0; y = 1'b1; w = 1'b0; z = 1'b1;
		#1

   		x = 1'b0; y = 1'b1; w = 1'b1; z = 1'b0;
		#1

    	x = 1'b0; y = 1'b1; w = 1'b1; z = 1'b1;
		#1

   		x = 1'b1; y = 1'b0; w = 1'b0; z = 1'b0;
		#1

   		x = 1'b1; y = 1'b0; w = 1'b0; z = 1'b1;
		#1

    	x = 1'b1; y = 1'b0; w = 1'b1; z = 1'b0;
		#1

    	x = 1'b1; y = 1'b0; w = 1'b1; z = 1'b1;
		#1

    	x = 1'b1; y = 1'b1; w = 1'b0; z = 1'b0;
		#1

    	x = 1'b1; y = 1'b1; w = 1'b0; z = 1'b1;
		#1

    	x = 1'b1; y = 1'b1; w = 1'b1; z = 1'b0;
    	#1

   		x = 1'b1; y = 1'b1; w = 1'b1; z = 1'b1;
  	end
endmodule 




/*
Saida do Programa:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> iverilog -o  guia .\Guia_0606.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> vvp guia
   x    y    w    z    s1   s2
   0    0    0    0    0    1
   0    0    0    1    0    1
   0    0    1    0    0    1
   0    0    1    1    0    1
   0    1    0    0    0    1
   0    1    0    1    0    1
   0    1    1    0    0    1
   0    1    1    1    0    0
   1    0    0    0    1    1
   1    0    0    1    1    1
   1    0    1    0    1    1
   1    0    1    1    1    1
   1    1    0    0    1    1
   1    1    0    1    1    1
   1    1    1    0    1    1
   1    1    1    1    0    1
*/