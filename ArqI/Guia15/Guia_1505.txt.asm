
START:	   MVI A,10	// Carrega dado01 em A
	   MOV B,A	// Move dado01 para B
	   ADD B	// Adiciona dado01 a si mesmo (dobro)
	   ADD B	// Adiciona dado01 dobrado a si mesmo (quádruplo)
	   MOV C,A	// Armazena o quádruplo em C
	   MVI A,20	// Carrega dado02 em A
	   RRC	// Divide por 2 (desloca um bit para a direita)
	   MOV B,A	// Move dado02/2 para B
	   MOV A,C	// Move o quádruplo de dado01 de volta para A
	   ADD B	// Adiciona dado02/2 a dado01*4
	   STA 0001	// Armazena o resultado em dado03
	   HLT	// Termina o programa
