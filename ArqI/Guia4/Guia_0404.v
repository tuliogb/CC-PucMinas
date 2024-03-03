/*
Nome: Tulio Gomes Braga
Matricula: 802512
*/

module b (output s, input x, y, z);
    assign s = (x|y|z) & (~x|y|z) & (~x|y|~z) & (~x|~y|~z);
endmodule

module d (output s, input x, y, z, w);
    assign s = (x|y|w|~z) & (x|y|~w|z) & (x|~y|~w|z) & (~x|y|w|z) & (~x|y|w|~z) & (~x|~y|~w|z);
endmodule


module Guia_0404;

    reg x, y, z, w;
    wire s_b, s_d;

    b funcao2 (.s(s_b), .x(x), .y(y), .z(z));
    d funcao4 (.s(s_d), .x(x), .y(y), .z(z), .w(w));

    initial begin : main
        x = 0;
        y = 1;
        z = 1;
        w = 0;

        #10;
        $display("Resultado de b: %b", s_b);
        $display("Resultado de d: %b", s_d);
    end
endmodule


/*
Saida ddo Teste:
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas>iverilog -o guia .\Guia_0404.v
PS C:\Users\Túlio\Documents\Faculdade\2º Semestre\Arquitetura de Computadores I\Tarefas>vvp .\guia
Resultado de b: 1
Resultado de d: 1
*/