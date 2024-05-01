/*
    802512
    Tulio Gomes Braga
*/

module a (output saida, input a, input b, input c, input d);
	wire w1, w2, w3;
	nand NAND_1 (w1, a, c);
	nand NAND_2 (w2, b, d);
	nand NAND_3 (w3, w1, w2);
	assign saida = w3;
endmodule 


module mux (output reg out, input sel, input a, input b);
    always @* begin
        case(sel)
            0: out = a;
            1: out = b;
            default: out = 1'b0; 
        endcase
    end
endmodule


module b (output saida, input a, input b, input c);
    wire m1, m2, m3;
    mux um (.out(m1), .sel(a), .a(b), .b(c));
    mux dois (.out(m2), .sel(c), .a(~a), .b(~a));
    mux tres (.out(m3), .sel(~c), .a(m1), .b(m2));
    assign saida = m3;
endmodule


module test;
    reg a, b, c, d;
    wire sa, sb;	
    integer i;

    a letraa (.saida(sa), .a(a), .b(b), .c(c), .d(d));
    b letrab (.saida(sb), .a(a), .b(b), .c(c));
    
    initial begin
        $display("a  b  c  d  sa");
        for (i = 0; i < 16; i = i + 1) begin
            a = i[3]; b = i[2]; c = i[1]; d = i[0]; #1;
            $display("%b  %b  %b  %b  %b", a, b, c, d, sa);
        end
        
        $display("\n----------------\n");

        $display("a  b  c  sb");
        for (i = 0; i < 8; i = i + 1) begin
            a = i[2]; b = i[1]; c = i[0]; #1;
            $display("%b  %b  %b  %b", a, b, c, sb);
        end
    end
endmodule



/*
tulio@tulio-550XDA:~/Área de Trabalho/R01_802512_TulioGomesBraga$ iverilog -o guia R01_0103.v 
tulio@tulio-550XDA:~/Área de Trabalho/R01_802512_TulioGomesBraga$ vvp guia 
a  b  c  d  sa
0  0  0  0  0
0  0  0  1  0
0  0  1  0  0
0  0  1  1  0
0  1  0  0  0
0  1  0  1  1
0  1  1  0  0
0  1  1  1  1
1  0  0  0  0
1  0  0  1  0
1  0  1  0  1
1  0  1  1  1
1  1  0  0  0
1  1  0  1  1
1  1  1  0  1
1  1  1  1  1

----------------

a  b  c  sb
0  0  0  1
0  0  1  0
0  1  0  1
0  1  1  1
1  0  0  0
1  0  1  1
1  1  0  0
1  1  1  1
/*

