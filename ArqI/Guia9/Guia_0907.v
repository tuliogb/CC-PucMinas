/*
	Nome: Tulio Gomes Braga 
	Matricula: 802512	
*/

`include "clock.v"

module pulse1 ( signal, clock );
    input clock;
    output signal;
    reg signal;
    
    always @ ( clock ) begin
        signal = 1'b1;
        #6 signal = 1'b0;
    end
endmodule

module Guia_0907;
    wire clock;
    clock clk ( clock );
    wire p1;

    pulse1 pls1( p1, clock );

    initial begin
        $dumpfile ( "Guia_0907.vcd" );
        $dumpvars ( 1, clock, p1);	
        #240 $finish;
    end
endmodule 




/*
	Sempre que houver um giro de clock o pulse 1 ira produzir pulsos de 6 em 6 segundos.
*/
