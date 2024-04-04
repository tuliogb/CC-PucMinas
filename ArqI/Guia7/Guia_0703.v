module funcao2(output wire ss1, input wire select, input wire a, input wire b);
    wire um = ~(a | a);
    wire dois = (um | select);
    wire tres = ~(dois | dois);
    
    wire quatro = ~(b | b);
    wire cinco = ~(select | select);
    wire seis = (quatro | cinco);
    wire sete = ~(seis | seis);

    assign ss1 = (tres | sete);
    
endmodule


module funcao(output wire ss1, input wire select, input wire a, input wire b);
    wire um = ~(select & select);
    wire dois = ~(um & a);
    wire tres = ~(b & select);
    
    wire saida1 = ~(tres & dois);
    
    wire mux2;
    funcao2 inst_funcao2(.ss1(mux2), .select(select), .a(a), .b(b));
    
    wire saida2;
    funcao2 inst_funcao2_2(.ss1(saida2), .select(select), .a(mux2), .b(saida1));
    
    assign ss1 = saida2;
    
endmodule


module Guia_0703;

    reg x, y, select;
    wire s1;
    funcao teste(.ss1(s1), .select(select), .a(x), .b(y));
    
    initial begin : main

        x = 0;
        y = 1;
        select = 1;
        #10;
        $display("Resultado 00 chave 1: s1=%b", s1);

        x = 1;
        y = 0;
        select = 1;
        #10;
        $display("Resultado 10 chave 1: s1=%b", s1);

        x = 1;
        y = 1;
        select = 0;
        #10;
        $display("Resultado 11 chave 0: s1=%b", s1);
        
        x = 0;
        y = 0;
        select = 0;
        #10;
        $display("Resultado 00 chave 0: s1=%b", s1);
    
    end
endmodule



/*
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0703.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Resultado 01 chave 1: s1=1
Resultado 10 chave 1: s1=0
Resultado 11 chave 0: s1=1
Resultado 01 chave 1: s1=1

*/
