	   MVI A,10 // acumulador recebe valor 10
	   MOV B,A // salvo o valor em b
	   MVI A,05 // acumulador recebe o valor 5
	   ADD A	// atribui valor de a ao acumulador
	   MOV C,A  // move o valor de a pra c
	   MOV A,B // move o valor de b de volta pro acumulador
	   RLC // realiza a rotacao dos bits
	   ADD B // incrementa b
	   ADD B // incrementa b (=4*)
	   STA 0030 // salva no endereco 0030
	   HLT
