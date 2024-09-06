/*
    Nome: Tulio Gomes Braga
    Matricula: 802512
*/


module hdd ( output carry, output sum, input a, input b ); 
    xor X0 (  sum , a, b ); 
    and A0 ( carry, a, b ); 
endmodule


module fdd ( output carry, output sum, input a,  input b,  input carryIn ); 
    wire w1,w2,w3;
    hdd H0 ( w1, w2, a, b );
    hdd H1 ( w3, sum, w2, carryIn );
    or        OR1 ( carry, w1, w3 );
endmodule 


module c1 ( output [4:0] s, input [4:0] a );
    not N1 ( s[0], a[0] );
    not N2 ( s[1], a[1] );
    not N3 ( s[2], a[2] );
    not N4 ( s[3], a[3] );
    not N5 ( s[4], a[4] );
endmodule 

 
module c2 ( output carryOut, output [4:0] s, input [4:0] a ); 
    wire c1, c2, c3, c4;
    fdd F0 (    c1   , s[0], a[0], 1'b1, 1'b0 );
    fdd F1 (    c2   , s[1], a[1], 1'b0,  c1  );
    fdd F2 (    c3   , s[2], a[2], 1'b0,  c2  );
    fdd F3 (    c4   , s[3], a[3], 1'b0,  c3  );
    fdd F4 ( carryOut, s[4], a[4], 1'b0,  c4  );
endmodule 



 
module Guia_0805; 
    reg  [4:0] x;
    wire [4:0] xc1; 
    wire [4:0] xc2; 
    wire carryOut;

    c1 C1 ( xc1, x );
    c2 C2 ( carryOut, xc2, xc1 );

    initial begin : start
        x = 5'b00000;
    end 

 
    initial begin : main 
        $display("Resultado do complemento de 2: "); 

        $display( "  x   >>    c2" );
        $monitor( "%b >> (%b)%b", x, carryOut, xc2 );
        for( integer i = 0; i < 32; i++ ) begin
            { x } = i;
            #1;
        end 
    end 

endmodule 


/*
Saida testada:
PS C:\Users\Túlio\Desktop\v> iverilog -o guia .\Guia_0805.v
PS C:\Users\Túlio\Desktop\v> vvp .\guia
Resultado do complemento de 2:
  x   >>    c2
00000 >> (1)00000
00001 >> (0)11111
00010 >> (0)11110
00011 >> (0)11101
00100 >> (0)11100
00101 >> (0)11011
00110 >> (0)11010
00111 >> (0)11001
01000 >> (0)11000
01001 >> (0)10111
01010 >> (0)10110
01011 >> (0)10101
01100 >> (0)10100
01101 >> (0)10011
01110 >> (0)10010
01111 >> (0)10001
10000 >> (0)10000
10001 >> (0)01111
10010 >> (0)01110
10011 >> (0)01101
10100 >> (0)01100
10101 >> (0)01011
10110 >> (0)01010
10111 >> (0)01001
11000 >> (0)01000
11001 >> (0)00111
11010 >> (0)00110
11011 >> (0)00101
11100 >> (0)00100
11101 >> (0)00011
11110 >> (0)00010
11111 >> (0)00001
*/

