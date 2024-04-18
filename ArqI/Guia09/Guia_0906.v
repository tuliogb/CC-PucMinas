/*
	Nome: Tulio Gomes Braga 
	Matricula: 802512	
*/

`include "clock.v"

module pulse1 ( signal, clock );
    input clock;
    output signal;
    reg signal;
    
    always @ ( negedge clock ) begin
        signal = 1'b1;
        #3 signal = 1'b0;
        #3 signal = 1'b1;
        #3 signal = 1'b0;
        #3 signal = 1'b1;
    end
endmodule

module Guia_0906;
    wire clock;
    clock clk ( clock );
    wire p1;

    pulse1 pls1( p1, clock );

    initial begin
        $dumpfile ( "Guia_0906.vcd" );
        $dumpvars ( 1, clock, p1);	
        #240 $finish;
    end
endmodule 




/*
	Quando o clock estiver em baixa(0) o pulse 1 vai ativar fazendo pulsos intercalados de 3 em 3 s.
*/
