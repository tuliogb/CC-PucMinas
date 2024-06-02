/*
    Nome: Tulio Gomes Braga
    Matricula: 802512
*/

module hdd ( output carry, output sum, input a, input b ); 
    xor XOR1 ( sum, a, b ); 
    and AND1 ( carry, a, b ); 
endmodule 


module fdd ( output carry, output sum, input a,  input b,  input carryIn ); 
    wire w1,w2,w3;
    hdd H0 ( w1, w2, a, b );
    hdd H1 ( w3, sum, w2, carryIn );
    or OR1 ( carry, w1, w3 );
endmodule 


module Guia_0800; 

    reg  [2:0] x; 
    reg  [2:0] y; 
    wire [2:0] carry; 
    wire [3:0] soma; 

    fdd F0 ( carry[0], soma[0], x[0], y[0], 1'b0     ); 
    fdd F1 ( carry[1], soma[1], x[1], y[1], carry[0] ); 
    fdd F2 ( carry[2], soma[2], x[2], y[2], carry[1] ); 
    assign soma[3] = carry[2];    
    
    initial begin : start
        x = 3'b000;
        y = 3'b000;
    end 

    initial begin : main  
        $display("Resultado das somas: "); 

        $display( " x  +  y  = soma" );
        $monitor( "%b + %b = %b", x, y, soma );
        for( integer i = 0; i < 8; i++ ) begin
            { x } = i;
            { y } = i;
            #1;
        end 
    end 
endmodule 


/*
Saida testada:
PS C:\Users\Túlio\Desktop\v> iverilog -o guia .\Guia_0800.v
PS C:\Users\Túlio\Desktop\v> vvp .\guia
Resultado das somas:
 x  +  y  = soma
000 + 000 = 0000
001 + 001 = 0010
010 + 010 = 0100
011 + 011 = 0110
100 + 100 = 1000
101 + 101 = 1010
110 + 110 = 1100
111 + 111 = 1110
*/