/*
	Tulio Gomes Braga
	802512
*/


module funcao(output wire ss1, input wire select, input wire a, input wire b);
    wire um = ~(a | a);
    wire dois = (um | select);
    wire tres = ~(dois | dois);
    
    
    
    wire quatro = ~(b | b);
    wire cinco = ~(select | select);
    wire seis = (quatro | cinco);
    wire sete = ~(seis | seis);

    assign ss1 = (tres | sete);
    
endmodule


module Guia_0702;

    reg x, y, select;
    wire s1, s2;
    funcao teste(.ss1(s1), .select(select), .a(x), .b(y));
    
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
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_0702.v
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Resultado 01 chave 1: s1=1, s2=z
Resultado 10 chave 1: s1=0, s2=z
Resultado 11 chave 0: s1=1, s2=z
Resultado 01 chave 1: s1=1, s2=z
*/
