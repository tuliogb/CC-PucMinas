/*
    802512
    Tulio Gomes Braga
*/

module a (output s, input a, input b, input c, input d);
    assign s = (~a & ~b & ~c & ~d) | (~a & ~b & ~c & d) | (~a & ~b & c & ~d) | (~a & ~b & c & d) | (~a & b & ~c & d) | (a & ~b & ~c & ~d) | (a & b & ~c & d);
endmodule

module b (output s, input A, input B, input C, input D);
    assign s = (A | ~B | C | D) & (A | ~B | ~C | D) & (A | ~B | ~C | ~D) & (~A | B | C | ~D) & (~A | B | ~C | D) & (~A | B | ~C | ~D) & (~A | ~B | C | D) & (~A | ~B | ~C | D) & (~A | ~B | ~C | ~D);
endmodule

module c ( output s, input a, input b, input c, input d);
    assign s = (~a & ~b) | (b & ~c & d) | (~b & ~c & ~d);
endmodule

module d (output s, input A, input B, input C, input D);
    assign s = (~B | ~C) & (~A | B | ~C) & (~A | B | ~D) & (~B | C | D);
endmodule

module e (output s, input a, input b, input c, input d);
    wire nand1, nand2, nand3, nand4, nand5, nand6, nand7, nand8;

    assign nand1 = ~(~a & ~b & ~c & ~d);
    assign nand2 = ~(~a & ~b & ~c & d);
    assign nand3 = ~(~a & ~b & c & ~d);
    assign nand4 = ~(~a & ~b & c & d);
    assign nand5 = ~(~a & b & ~c & d);
    assign nand6 = ~(a & ~b & ~c & ~d);
    assign nand7 = ~(a & b & ~c & d);

    assign s = nand1 | nand2 | nand3 | nand4 | nand5 | nand6 | nand7;
endmodule

module f (output s, input A,  input B, input C, input D);
    wire nor1, nor2, nor3, nor4, nor5, nor6, nor7, nor8, nor9;
    
    assign nor1 = ~(A | ~B | C | D);
    assign nor2 = ~(A | ~B | ~C | D);
    assign nor3 = ~(A | ~B | ~C | ~D);
    assign nor4 = ~(~A | B | C | ~D);
    assign nor5 = ~(~A | B | ~C | D);
    assign nor6 = ~(~A | B | ~C | ~D);
    assign nor7 = ~(~A | ~B | C | D);
    assign nor8 = ~(~A | ~B | ~C | D);
    assign nor9 = ~(~A | ~B | ~C | ~D);

    assign s = nor1 & nor2 & nor3 & nor4 & nor5 & nor6 & nor7 & nor8 & nor9; 
endmodule

module execucao;
    reg a, b, c, d;
    wire s1, s2, s3, s4, s5, s6;

    a modulea (s1, a, b, c, d);
    b moduleb (s2, a, b, c, d);
    c modulec (s3, a, b, c, d);
    d moduled (s4, a, b, c, d);
    e modulee (s5, a, b, c, d);
    f modulef (s6, a, b, c, d);


    initial begin
    $display("a b c d s1 s2 s3 s4 s5");
    $monitor("%b %b %b %b %b %b %b %b %b %b", a, b, c, d, s1, s2, s3, s4, s5, s6);
	a = 1'b0; b = 1'b0; c = 1'b0; d = 1'b0;
	#1
	a = 1'b0; b = 1'b0; c = 1'b0; d = 1'b1;
	#1
    	a = 1'b0; b = 1'b0; c = 1'b1; d = 1'b0;
	#1
    	a = 1'b0; b = 1'b0; c = 1'b1; d = 1'b1;
	#1
    	a = 1'b0; b = 1'b1; c = 1'b0; d = 1'b0;
	#1
    	a = 1'b0; b = 1'b1; c = 1'b0; d = 1'b1;
	#1
    	a = 1'b0; b = 1'b1; c = 1'b1; d = 1'b0;
	#1
    	a = 1'b0; b = 1'b1; c = 1'b1; d = 1'b1;
	#1
    	a = 1'b1; b = 1'b0; c = 1'b0; d = 1'b0;
	#1
    	a = 1'b1; b = 1'b0; c = 1'b0; d = 1'b1;
	#1
    	a = 1'b1; b = 1'b0; c = 1'b1; d = 1'b0;
	#1
    	a = 1'b1; b = 1'b0; c = 1'b1; d = 1'b1;
	#1
    	a = 1'b1; b = 1'b1; c = 1'b0; d = 1'b0;
	#1
    	a = 1'b1; b = 1'b1; c = 1'b0; d = 1'b1;
	#1
    	a = 1'b1; b = 1'b1; c = 1'b1; d = 1'b0;
    	#1
    	a = 1'b1; b = 1'b1; c = 1'b1; d = 1'b1;
    end
endmodule


/*
tulio@tulio-550XDA:~/Área de Trabalho/R01_802512_TulioGomesBraga$ iverilog -o guia R01_0101.v 
tulio@tulio-550XDA:~/Área de Trabalho/R01_802512_TulioGomesBraga$ vvp guia 
a b c d s1 s2 s3 s4 s5
0 0 0 0 1 1 1 1 1 0
0 0 0 1 1 1 1 1 1 0
0 0 1 0 1 1 1 1 1 0
0 0 1 1 1 1 1 1 1 0
0 1 0 0 0 0 0 0 1 0
0 1 0 1 1 1 1 1 1 0
0 1 1 0 0 0 0 0 1 0
0 1 1 1 0 0 0 0 1 0
1 0 0 0 1 1 1 1 1 0
1 0 0 1 0 0 0 0 1 0
1 0 1 0 0 0 0 0 1 0
1 0 1 1 0 0 0 0 1 0
1 1 0 0 0 0 0 0 1 0
1 1 0 1 1 1 1 1 1 0
1 1 1 0 0 0 0 0 1 0
1 1 1 1 0 0 0 0 1 0
*/
