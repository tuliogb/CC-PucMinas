`define found 1
`define notfound 0

module moore_1101 (y, x, clk, reset);
  output y;
  input x;
  input clk;
  input reset;
  
  reg y;
  parameter
    start = 3'b000,
    id1 = 3'b001,
    id11 = 3'b011,
    id110 = 3'b010,
    id1101 = 3'b110;
  
  reg [2:0] E1; 
  reg [2:0] E2;
  
  always @(x or E1) begin
    case(E1)
      start:
        if (x)
          E2 = id1;
        else
          E2 = start;
      id1:				//  Ele so entra nesse estado se a linha 25 for executada ?
        if (x)
          E2 = id11;
        else
          E2 = start;
      id11:
        if (x)
          E2 = id11;
        else
          E2 = id110;
      id110:
        if (x)
          E2 = id1101;			
        else
          E2 = start;
      id1101:
        if (x)
          E2 = id11;		
        else
          E2 = start;
      default: 
        E2 = 3'bxxx;
    endcase
  end
  
  
  always @(posedge clk or negedge reset) begin
    if (reset)
      E1 = E2; 		// updates current state
    else
      E1 = 0; 		// reset
  end
  
  
  always @(E1) begin
    y = E1[2]; 		// first bit of state value (MOORE indicator)
  end 			// always at state changing
endmodule 



module execucao();
    reg x;
    reg clk = 0;
    reg reset = 0;
    wire y;

    moore_1101 funcao (.y(y), .x(x), .clk(clk), .reset(reset));
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
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ iverilog -o guia Guia_1102.v 
tulio@tulio-550XDA:~/Documentos/Faculdade/Arquitetura 1/Tarefas$ vvp guia 
Resultado:= 0
Resultado:= 1
Resultado:= 0
Resultado:= 1
*/



