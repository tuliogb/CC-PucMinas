`include "mealy_1101.v"
`include "moore_1101.v"

module Guia_1101;
	reg clk, reset, x;
	wire m1, m2;
	
	mealy_1101 mealy1 ( m1, x, clk, reset );
	moore_1101 moore1 ( m2, x, clk, reset );
	
	initial begin
		$display ( "TimeX Mealy Moore" );

		clk = 1;
		reset = 0;
		x = 0;

		#5 reset = 1;
		#10 x = 1;
		#10 x = 0;
		#10 x = 1;
		#20 x = 0;
		#10 x = 1;
		#10 x = 1;
		#10 x = 0;
		#10 x = 1;
		
		#30 $finish;
	end
	
	always #5 clk = ~clk;
	always @( posedge clk ) begin
		$display ( "%4d %4b %4b %5b", $time, x, m1, m2 );
	end 
endmodule 

/*
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_1103.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia
TimeX Mealy Moore
  10    0    0     0
  20    1    0     0
  30    0    0     0
  40    1    0     0
  50    1    0     0
  60    0    0     0
  70    1    1     0
  80    1    0     1
  90    0    0     0
 100    1    1     0
 110    1    0     1
 120    1    0     0
*/
