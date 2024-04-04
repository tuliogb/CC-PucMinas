/*
	Tulio Gomes Braga
	802512
*/


module funcao(output wire ss1, output wire ss2, input wire select, input wire a, input wire b);
    wire nchave = ~select;
    assign ss1 = a & nchave;
    assign ss2 = b & select;
    
endmodule

module Guia_0701;

    reg x, y, select;
    wire s1, s2;
    funcao teste(.ss1(s1), .ss2(s2), .select(select), .a(x), .b(y));
    
    initial begin : main

        x = 0;
        y = 1;
        select = 1;
        #10;
        $display("Resultado 01 chave 1: s1=%b, s2=%b", s1, s2);

        x = 1;
        y = 0;
        select = 1;
        #10;
        $display("Resultado 10 chave 1: s1=%b, s2=%b", s1, s2);

        x = 1;
        y = 1;
        select = 0;
        #10;
        $display("Resultado 11 chave 0: s1=%b, s2=%b", s1, s2);
        
        x = 0;
        y = 1;
        select = 1;
        #10;
        $display("Resultado 01 chave 1: s1=%b, s2=%b", s1, s2);
    
    end
endmodule



/*
Saida:
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0701.v
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp 
guia          Guia_0701.v   Guia_0702.v   Guia_07.circ  
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Resultado 01 chave 1: s1=0, s2=1
Resultado 10 chave 1: s1=0, s2=0
Resultado 11 chave 0: s1=1, s2=0
Resultado 01 chave 1: s1=0, s2=1
*/

