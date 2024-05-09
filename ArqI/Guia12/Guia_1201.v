/*
	Tulio Gomes Braga
	802512
*/

module jkff ( output q, output qnot, input j, input k, input clk, input preset, input clear );
	reg q, qnot;

	always @( posedge clk or posedge preset or posedge clear ) begin
 		if ( clear ) begin q <= 0; qnot <= 1; end
 		else 
 			if ( preset ) begin q <= 1; qnot <= 0; end
 			else 
 				if ( j & ~k ) begin q <= 1; qnot <= 0; end
 				else
 					if ( ~j & k ) begin q <= 0; qnot <= 1; end
 					else
 						if ( j & k ) begin q <= ~q; qnot <= ~qnot; end
	end
endmodule 




module execucao();
    reg j, k;
    wire q, qnot;
    reg clk=0, preset, clear;   

    jkff um(q, qnot, j, k, clk, preset, clear);
    jkff dois(q, qnot, j, k, clk, preset, clear);
    jkff tres(q, qnot, j, k, clk, preset, clear);
    jkff quatro(q, qnot, j, k, clk, preset, clear);

    always #5 clk = ~clk;

    initial begin
        preset = 0; 
        #2
        preset = 1;
        #2
        clear = 0;   
        #2
        clear = 1;   
        #2

        $monitor("Time: %t, Q: %b, Qnot: %b", $time, q, qnot);

        j=1; k=0;
        #2
        j=0; k=1;
        #2

        j=1; k=0;
        #2
        j=0; k=1;
        
        #60 $finish;
    end
endmodule
