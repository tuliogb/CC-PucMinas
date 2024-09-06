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


module pulse2 ( signal, clock );
    input clock;
    output signal;
    reg signal;

    always @ ( posedge clock ) begin
        signal = 1'b1;  
        #5 signal = 1'b0;
    end
endmodule 


module pulse3 ( signal, clock );
    input clock;
    output signal;
    reg signal;

    always @ ( negedge clock ) begin
        signal = 1'b1;
        #15 signal = 1'b0;
        #15 signal = 1'b1;
    end
endmodule 


module pulse4 ( signal, clock );
    input clock;
    output signal;
    reg signal;

    always @ ( negedge clock ) begin
        signal = 1'b1;
        #20 signal = 1'b0;
        #20 signal = 1'b1;
        #20 signal = 1'b0;  
    end
endmodule




module Guia_0902;
    wire clock;
    clock clk ( clock );
    wire p1,p2,p3,p4;

    pulse1 pls1( p1, clock );
    pulse2 pls2( p2, clock );
    pulse3 pls3( p3, clock );
    pulse4 pls4( p4, clock );

    initial begin
        $dumpfile ( "Guia_0902.vcd" );
        $dumpvars ( 1, clock, p1, p2, p3, p4 ); 
        #480 $finish;
    end
endmodule 

/*
Pulso 1: O seu laco de execucao dura o mesmo tanto que o clock so que em frequencias diferentes, mas o tempo de comeco é igual.
Pulso 2: Dura menos que o clock, podemos ver que quando o clock esta ativado ele tambem liga e aguarda um novo clock pra novamente ligar.
Pulso 3: Comeca com pulso 1 espera 15s, pulso 0 por 15s e seta como 1 ate aguarda o clock estar em baixa pra comecar novamente Vemos esse aguardo do 69s ate o 72s
Pulso 4: Quando o clock estiver em baixa, é gerado um pulso de 1, espera 20s, pulso de 0, espera 20s, pulso de 1 espera 20s e um pulso de 0 que aguarda o clock ficar em baixa pra comecar novamente.
*/



