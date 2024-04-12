/*
	Nome: Tulio Gomes Braga 
	Matricula: 802512	
*/

`include "clock.v"

module pulse1 ( signal, clock );
    input clock;
    output signal;
    reg signal;
    
    always @ ( posedge clock ) begin
        signal = 1'b1;
        #4 signal = 1'b0;
        #4 signal = 1'b1;
        #4 signal = 1'b0;
        #4 signal = 1'b1;
	#4 signal = 1'b0;
    end
endmodule

module Guia_0904;
    wire clock;
    clock clk ( clock );
    wire p1;

    pulse1 pls1( p1, clock );

    initial begin
        $dumpfile ( "Guia_0904.vcd" );
        $dumpvars ( 1, clock, p1); 	
        #480 $finish;
    end
endmodule 


/*
	24/3=8 >> 	clock:	1	0
			p1:	101	010
*/
