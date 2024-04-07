/*
	Nome: Tulio Gomes Braga
	Matricula :802512
*/

module somadorCmp (output s, output cout, input a, input b, input cin);
	assign s = (~a&~b&cin) | (~a&b&~cin) | (a&~b&~cin) | (a&b&cin);
	assign cout = (~a&b&cin) | (a&~b&cin) | (a&b&~cin) | (a&b&cin);
endmodule



module Guia_0801;

    reg [4:0] x, y;
    wire [4:0] saida;
    wire terra, um, dois, tres, quatro, cinco;

    somadorCmp f0 (.s(saida[0]), .cout(um), .a(x[0]), .b(y[0]), .cin(terra));
    somadorCmp f1 (.s(saida[1]), .cout(dois), .a(x[1]), .b(y[1]), .cin(um));
    somadorCmp f2 (.s(saida[2]), .cout(tres), .a(x[2]), .b(y[2]), .cin(dois));
    somadorCmp f3 (.s(saida[3]), .cout(quatro), .a(x[3]), .b(y[3]), .cin(tres));
    somadorCmp f4 (.s(saida[4]), .cout(cinco), .a(x[4]), .b(y[4]), .cin(quatro));

    initial begin
        x = 5'b10000;
        y = 5'b00001;

        #10;
        $display("S0 = %b", saida[0]);
        #10;
        $display("S1 = %b", saida[1]);
        #10;
        $display("S2 = %b", saida[2]);
        #10;
        $display("S3 = %b", saida[3]);
        #10;
        $display("S4 = %b", saida[4]);
    end

endmodule

