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
        #48 signal = 1'b0;
        #48 signal = 1'b1;
    end
endmodule

module Guia_0903;
    wire clock;
    clock clk ( clock );
    wire p1;

    pulse1 pls1( p1, clock );

    initial begin
        $dumpfile ( "Guia_0903.vcd" );
        $dumpvars ( 1, clock, p1); 
        #480 $finish;
    end
endmodule 


/*
	A cada um periodo do p1 sao realizados 4 periodos do clock, podemos ver isso do segundo 12 ate o 108 >> 108-12 = 96, periodo  de p1.
*/



