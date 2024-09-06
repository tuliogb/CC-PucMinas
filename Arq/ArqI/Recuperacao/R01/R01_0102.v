/*
    802512
    Tulio Gomes Braga
*/

module f ( output s, input x, input y );
	wire w1, w2, w3, w4;
	not NOT_1 (w1, y);
	and AND_1 (w2, x, w1);
	not NOT_2 (w3, w2);
	or OR__1 (w4, y, x);
	or OR__2 (s, w3, w4);
endmodule // s = f (x,y)

module execucao;
    reg x, y;
    wire s;
    
    f module1 (s, x, y);
    
    initial begin
        $display("x - y - s");
        $monitor("%b  %b  %b", x, y, s);
        #1 x=0; y=0;
        #1 x=0; y=1;
        #1 x=1; y=0;
        #1 x=1; y=1;
    end
endmodule

/*
tulio@tulio-550XDA:~/Área de Trabalho/R01_802512_TulioGomesBraga$ iverilog -o guia R01_0102.v 
tulio@tulio-550XDA:~/Área de Trabalho/R01_802512_TulioGomesBraga$ vvp guia 
x - y - s
x  x  x
0  0  1
0  1  1
1  0  1
1  1  1
*/
