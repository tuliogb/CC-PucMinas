/*
	Tulio Gomes Braga
	802512
*/

`define found 1
`define notfound 0
 
module mealy_1101 ( y, x, clk, reset );
	output y;
	input x;
	input clk;
	input reset;
	reg y;			// Variavel de saida
 
	parameter 
		start = 2'b00,	
		id1 = 2'b01,
		id11 = 2'b11,
		id110 = 2'b10;
 
		reg [1:0] E1;		// Estado atual
		reg [1:0] E2;		// Proximo estado
 
	always @( x or E1 ) begin
		y = `notfound;
	 	case ( E1 )
	 	start:
	 		if ( x ) E2 = id1;		// se x for true(1) proximo estado é o segundo;
	 		else E2 = start;		// se nao sera manter no primeiro;
 
	 	id1:
	 		if ( x ) E2 = id11;		// se x for true(1) passa pro terceiro;
	 		else E2 = start;		// se nao volta pro primeiro;
 
	 	id11:
	 		if ( x ) E2 = id11; 		// se x for true(1) continua nesse estagio;
	 		else E2 = id110;		// se nao vai pro quarto estagio (0);
 
	 	id110:
	 		if ( x ) begin			
	 			E2 = id1;		// volta pro estagio um para um possivel intercessao
	 			y = `found;		// achou (1)
	 		end
	 		else begin
	 			E2 = start;		// volta pro estagio inicial pra comecar de novo
	 			y = `notfound;
	 		end
 
	 	default: // undefined state
	 		E2 = 2'bxx;
	 	endcase
	end
 
	always @( posedge clk or negedge reset ) begin	// sempre que clock estiver em nivel alto e reset em nivel baixo
		if ( reset ) E1 = E2;
		else E1 = 0; 
	end
 
endmodule
 
 
module execucao();
    reg x;
    reg clk = 0;
    reg reset = 0;
    wire y;

    mealy_1101 funcao (.y(y), .x(x), .clk(clk), .reset(reset));
    always #5 clk = ~clk;

    initial begin
        $monitor("Resultado:= %b", y);
	
	#5
        reset = 1;
        #1; 
        
        x = 1;
        #10;

        x = 0;
        #10;

        x = 1;
        #10;

        x = 1;
        #10;

        x = 0;
        #10;

        x = 1;
        #10;

        x = 1;
        #10;

        x = 0;
        #10;

        x = 1;
        #10;
        
        $finish;
    end
endmodule


/*
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_1101.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Resultado:= 0
Resultado:= 1
Resultado:= 0
Resultado:= 1
Resultado:= 0
*/

