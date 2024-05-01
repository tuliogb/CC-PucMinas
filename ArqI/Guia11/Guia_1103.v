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
    id10 = 3'b010,
    id101 = 3'b001,
    id1010 = 3'b101;
  
  reg [2:0] E1; 
  reg [2:0] E2;
  
  always @(x or E1) begin
    case(E1)
      start:
        if (x)
          E2 = id1;
        else
          E2 = start;
      id1:
        if (x)
          E2 = id1;
        else
          E2 = id10;
      id10:
        if (x)
          E2 = id101;
        else
          E2 = start;
      id101:
        if (x)
          E2 = start;			
        else
          E2 = id1010;		// y recebe o 1 do comando "y = E1[2];"
      id1010:
        if (x)
          E2 = id101;		
        else
          E2 = start;
      default: 
        E2 = 3'bxxx;
    endcase
  end
  
  
  always @(posedge clk or negedge reset) begin
    if (reset)
      E1 = E2; 	
    else
      E1 = 0; 		
  end
  
  
  always @(E1) begin
    y = E1[2]; 	
  end 			
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

        x = 0;
        #10;

        x = 1;
        #10;

        x = 0;
        #10;

        x = 1;
        #10;

        x = 0;
        #10;

        x = 0;
        #10;
        
        x = 1;
        #10;
        
        $finish;
    end
endmodule


