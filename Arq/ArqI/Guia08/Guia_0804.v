/*
    Nome: Tulio Gomes Braga
    Matricula: 802512
*/

module isDif ( output s, input a, input b ); 
    xor X0 ( s, a, b ); 
endmodule  


module Guia_0804; 
    reg  [5:0] x; 
    reg  [5:0] y; 
    reg  [5:0] a; 
    reg  [5:0] b; 
    wire [5:0] s1;
    wire [5:0] s2; 
    
    isDif XY0 ( s1[0], x[0], y[0] );
    isDif XY1 ( s1[1], x[1], y[1] );
    isDif XY2 ( s1[2], x[2], y[2] );
    isDif XY3 ( s1[3], x[3], y[3] );
    isDif XY4 ( s1[4], x[4], y[4] );
    isDif XY5 ( s1[5], x[5], y[5] );

    isDif AB0 ( s2[0], a[0], b[0] );
    isDif AB1 ( s2[1], a[1], b[1] );
    isDif AB2 ( s2[2], a[2], b[2] );
    isDif AB3 ( s2[3], a[3], b[3] );
    isDif AB4 ( s2[4], a[4], b[4] );
    isDif AB5 ( s2[5], a[5], b[5] );


    initial begin : start
        x = 6'b101_010;
        y = 6'b101_010;

        a = 6'b100_111;
        b = 6'b100_100;
    end 


    initial begin : main 
    
        $display("Resultado da comparacao: "); 

        #1;
        $write( "%b  ==  %b ? %b = ", x, y, s1 );
		if( s1 == 6'b000000 ) begin
            $display( "True!" );
		end 
		else begin
            $display( "False!" );
		end 

        #1;
        $write( "%b  ==  %b ? %b = ", a, b, s2 );
		if( s2 == 6'b000000 ) begin
            $display( "True!" );
		end 
		else begin
            $display( "False!" );
		end 

    end 
endmodule 

/*
Saida testada:
PS C:\Users\Túlio\Desktop\v> iverilog -o guia .\Guia_0804.v
PS C:\Users\Túlio\Desktop\v> vvp .\guia
Resultado da comparacao:
101010  ==  101010 ? 000000 = True!
100111  ==  100100 ? 000011 = False!
*/