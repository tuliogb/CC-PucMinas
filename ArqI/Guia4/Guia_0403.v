/*
Nome: Tulio Gomes Braga
Matricula: 802512
*/

module a (output s, input x, y, z);
    assign s = (~x & y & ~z) | (x & y & ~z) | (x & y & z);
endmodule

module c (output s, input x, y, z, w);
    assign s = (~x & ~y & ~w & z) | (~x & ~y & w & ~z) | (~x & y & ~w & ~z) | (~x & y & w & ~z) | (~x & y & w & z) | (x & y & ~w & ~z) | (x & y & w & z);
endmodule

module e (output s, input x, y, z, w);
    assign s = (~x & ~y & ~w & ~z) | (~x & ~y & w & ~z) | (~x & y & ~w & z) | (~x & y & w & z) | (x & ~y & w & ~z) | (x & y & ~w & z) | (x & y & w & ~z);
endmodule

module Guia_0403;

    reg x, y, z, w;
    wire s_a, s_c, s_e;

    a funcao1 (.s(s_a), .x(x), .y(y), .z(z));
    c funcao3 (.s(s_c), .x(x), .y(y), .z(z), .w(w));
    e funcao5 (.s(s_e), .x(x), .y(y), .z(z), .w(w));

    initial begin : main
        x = 1;
        y = 1;
        z = 0;
        w = 1;

        #10;
        $display("Resultado de a: %b", s_a);
        $display("Resultado de c: %b", s_c);
        $display("Resultado de e: %b", s_e);
    end
endmodule


/*
Saida ddo Teste:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas>iverilog -o guia .\Guia_0403.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas>vvp .\guia
Resultado de a: 1
Resultado de c: 0
Resultado de e: 1
*/