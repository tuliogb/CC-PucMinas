/*
Nome: Tulio Gomes Braga
Matricula: 802512
*/

module b (output spos, output ssop, input x, y, z);
    assign ssop = (~x&~y) | (~x&y) | (x&y);
    assign spos = (~x|y);
endmodule

module e (output spos, output ssop, input x, y, w, z);
    assign ssop = (~x&~y&~w&~z) | (~x&~y&~w&z) | (x&y&~w&~z) | (~x&y&~w&~z) | (~x&y&~w&z) | (~x&y&w&z) | (~x&y&w&~z) | (x&~y&w&~z) | (x&y&w&~z) | (x&y&w&z) ;
    assign spos = (x|y|~w|z) & (~x|y|w|~z) & (x|~y|~w|~z) & (~x|y|~w|~z) & (~x|~y|w|z) & (~x|~y|w|~z) ;
endmodule


module Guia_0405;

    reg x, y, z, w;
    wire s_bp, s_bs, s_ep, s_es;

    b funcao2 (.spos(s_bp), .ssop(s_bs), .x(x), .y(y), .z(z));
    e funcao5 (.spos(s_ep), .ssop(s_es), .x(x), .y(y), .w(w), .z(z));

    initial begin : main
        x = 0;
        y = 1;
        z = 1;
        w = 0;

        #10;
        $display("Resultado de SopB: %b", s_bs);
        $display("Resultado de PosB: %b", s_bp);
        $display("Resultado de SopE: %b", s_es);
        $display("Resultado de PosE: %b", s_ep);
    end
endmodule


/*
Saida ddo Teste:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> iverilog -o guia .\Guia_0405.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas> vvp .\guia
Resultado de SopB: 1
Resultado de PosB: 1
Resultado de SopE: 1
Resultado de PosE: 1
*/