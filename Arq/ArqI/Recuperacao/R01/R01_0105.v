/*
    802512
    Tulio Gomes Braga
*/


module xoror(input [7:0] a, input [7:0] b, output sxor, output sor);
	assign sxor = a ^ b;
	assign sor = (a ^ b) | ((~a) ^ (~b));
endmodule


module execucao;
	reg [7:0] a, b;
	wire rxor, ror;

	xoror funcao(.a(a), .b(b), .sxor(rxor), .sor(ror));

	initial begin
	    $display("a b | XOR(a,b) XOR(-a,-b) | OR(XOR(a,b), XOR(-a,-b))");
	    $display("-----------------------------|-------------------------");
	    for (a = 0; a < 8; a = a + 1) begin
		for (b = 0; b < 8; b = b + 1) begin
		    #1;
		    $display("%d %d |    %d       %d       |          %d", a, b, rxor, ror, ror);
		end
	    end
	    $finish;
	end

endmodule


/*
tulio@tulio-550XDA:~/Área de Trabalho/R01_802512_TulioGomesBraga$ iverilog -o guia R01_0105.v 
tulio@tulio-550XDA:~/Área de Trabalho/R01_802512_TulioGomesBraga$ vvp guia 
a b | XOR(a,b) XOR(-a,-b) | OR(XOR(a,b), XOR(-a,-b))
-----------------------------|-------------------------
  0   0 |    0       0       |          0
  0   1 |    1       1       |          1
  0   2 |    0       0       |          0
  0   3 |    1       1       |          1
  0   4 |    0       0       |          0
  0   5 |    1       1       |          1
  0   6 |    0       0       |          0
  0   7 |    1       1       |          1
  1   0 |    1       1       |          1
  1   1 |    0       0       |          0
  1   2 |    1       1       |          1
  1   3 |    0       0       |          0
  1   4 |    1       1       |          1
  1   5 |    0       0       |          0
  1   6 |    1       1       |          1
  1   7 |    0       0       |          0
  2   0 |    0       0       |          0
  2   1 |    1       1       |          1
  2   2 |    0       0       |          0
  2   3 |    1       1       |          1
  2   4 |    0       0       |          0
  2   5 |    1       1       |          1
  2   6 |    0       0       |          0
  2   7 |    1       1       |          1
  3   0 |    1       1       |          1
  3   1 |    0       0       |          0
  3   2 |    1       1       |          1
  3   3 |    0       0       |          0
  3   4 |    1       1       |          1
  3   5 |    0       0       |          0
  3   6 |    1       1       |          1
  3   7 |    0       0       |          0
  4   0 |    0       0       |          0
  4   1 |    1       1       |          1
  4   2 |    0       0       |          0
  4   3 |    1       1       |          1
  4   4 |    0       0       |          0
  4   5 |    1       1       |          1
  4   6 |    0       0       |          0
  4   7 |    1       1       |          1
  5   0 |    1       1       |          1
  5   1 |    0       0       |          0
  5   2 |    1       1       |          1
  5   3 |    0       0       |          0
  5   4 |    1       1       |          1
  5   5 |    0       0       |          0
  5   6 |    1       1       |          1
  5   7 |    0       0       |          0
  6   0 |    0       0       |          0
  6   1 |    1       1       |          1
  6   2 |    0       0       |          0
  6   3 |    1       1       |          1
  6   4 |    0       0       |          0
  6   5 |    1       1       |          1
  6   6 |    0       0       |          0
  6   7 |    1       1       |          1
  7   0 |    1       1       |          1
  7   1 |    0       0       |          0
  7   2 |    1       1       |          1
  7   3 |    0       0       |          0
  7   4 |    1       1       |          1
  7   5 |    0       0       |          0
  7   6 |    1       1       |          1
  7   7 |    0       0       |          0
*/



