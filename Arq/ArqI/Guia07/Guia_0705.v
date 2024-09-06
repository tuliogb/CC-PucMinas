/*
	Tulio Gomes Braga
	802512
*/

module funcao;

	reg a, b;
	reg [2:0] chave;
	output wire result;

	wire not_b, selected_gate;

	assign not_b = ~b;

	assign resp = 
		(chave == 3'b000) ? (~a) : (chave == 3'b001) ? (a & not_b) :
	  	(chave == 3'b010) ? ~(a & not_b) : (chave == 3'b011) ? (a | not_b) :
	 	(chave == 3'b100) ? ~(a | not_b) :  (chave == 3'b101) ? (a ^ not_b) : 
	 	(chave == 3'b110) ? ~(a ^ not_b) : 1'b0;

assign result = resp;
