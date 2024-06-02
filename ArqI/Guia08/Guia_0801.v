/*
    Nome: Tulio Gomes Braga
    Matricula: 802512
*/

module hdd ( output carry, output sum, input a, input b ); 
    xor XOR0 (  sum , a, b ); 
    and AND0 ( carry, a, b ); 
endmodule 


module fdd ( output carry, output sum, input a,  input b,  input carryIn ); 
    wire w1,w2,w3;
    hdd H0 ( w1, w2, a, b );
    hdd H1 ( w3, sum, w2, carryIn );
    or      OR1 ( carry, w1, w3 );
endmodule 


module Guia_0801; 

    reg  [4:0] x; 
    reg  [4:0] y; 
    wire [4:0] carry; 
    wire [5:0] soma; 

    fdd F0 ( carry[0], soma[0], x[0], y[0], 1'b0     ); 
    fdd F1 ( carry[1], soma[1], x[1], y[1], carry[0] ); 
    fdd F2 ( carry[2], soma[2], x[2], y[2], carry[1] ); 
    fdd F3 ( carry[3], soma[3], x[3], y[3], carry[2] ); 
    fdd F4 ( carry[4], soma[4], x[4], y[4], carry[3] ); 
    assign soma[5] = carry[4];    


    initial begin : start
        x = 5'b00000;
        y = 5'b00000;
    end 


    initial begin : main 
        $display("Resultado das somas: "); 

        $display( "  x   +   y   =  soma" );
        $monitor( "%b + %b = %b", x, y, soma );
        for( integer i = 0; i < 32; i++ ) begin
            { x } = i;
            { y } = i;
            #1;
        end 
    end 
endmodule 


/*
Saida testada:
PS C:\Users\Túlio\Desktop\v> iverilog -o guia .\Guia_0801.v
PS C:\Users\Túlio\Desktop\v> vvp .\guia
Resultado das somas:
  x   +   y   =  soma
00000 + 00000 = 000000
00001 + 00001 = 000010
00010 + 00010 = 000100
00011 + 00011 = 000110
00100 + 00100 = 001000
00101 + 00101 = 001010
00110 + 00110 = 001100
00111 + 00111 = 001110
01000 + 01000 = 010000
01001 + 01001 = 010010
01010 + 01010 = 010100
01011 + 01011 = 010110
01100 + 01100 = 011000
01101 + 01101 = 011010
01110 + 01110 = 011100
01111 + 01111 = 011110
10000 + 10000 = 100000
10001 + 10001 = 100010
10010 + 10010 = 100100
10011 + 10011 = 100110
10100 + 10100 = 101000
10101 + 10101 = 101010
10110 + 10110 = 101100
10111 + 10111 = 101110
11000 + 11000 = 110000
11001 + 11001 = 110010
11010 + 11010 = 110100
11011 + 11011 = 110110
11100 + 11100 = 111000
11101 + 11101 = 111010
11110 + 11110 = 111100
11111 + 11111 = 111110
*/