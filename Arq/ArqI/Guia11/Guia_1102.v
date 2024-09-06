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
		id10 = 2'b10,
		id101 = 2'b01;
 
		reg [1:0] E1;		// Estado atual
		reg [1:0] E2;		// Proximo estado
 
	always @( x or E1 ) begin
		y = `notfound;
	 	case ( E1 )
	 	start:
	 		if ( x ) E2 = id1;		
	 		else E2 = start;		
 
	 	id1:
	 		if ( x ) E2 = id1;		
	 		else E2 = id10;			
 
	 	id10:
	 		if ( x ) E2 = id101; 		
	 		else E2 = start;		
 
	 	id101:
	 		if ( x ) begin			
	 			E2 = start;		
	 			y = `notfound;	
	 		end
	 		else begin
	 			E2 = id10;		
	 			y = `found;
	 		end
 
	 	default: // undefined state
	 		E2 = 2'bxx;
	 	endcase
	end
 
	always @( posedge clk or negedge reset ) begin	
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

        x = 0;
        #10;

        x = 1;
        #10;

        x = 0;
        #10;
       
        x = 0;
        #10;
        
        $finish;
    end
endmodule


