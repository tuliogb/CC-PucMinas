	   LXI H,8000	// Carrega o endereço de memória onde os dados estão armazenados
	   MOV A,M	// Move o primeiro byte do dado01 para o acumulador A
	   INX H	// Incrementa o ponteiro de memória para apontar para o próximo byte
	   MOV B,A	// Move o dado01 para o registrador B para uso posterior
	   LXI H,8002	// Carrega o endereço de memória onde o dado02 está armazenado
	   MOV A,M	// Move o primeiro byte do dado02 para o acumulador A
	   ADD B	// Adiciona o dado01 ao acumulador A
	   MOV B,A	// Move o resultado da adição para o registrador B
	   LXI H,8004	// Carrega o endereço de memória onde o dado03 está armazenado
	   MOV A,M	// Move o primeiro byte do dado03 para o acumulador A
	   CMA	// Complementa o acumulador A para obter o complemento de 2
	   INX H	// Incrementa o ponteiro de memória para apontar para o próximo byte
	   ADD B	// Adiciona o complemento de 2 do dado03 ao resultado anterior em B
	   MOV B,A	// Move o resultado da subtração para o registrador B
	   LXI H,8006	// Carrega o endereço de memória onde o dado04 será armazenado
	   MOV M,B	// Move o resultado final para o endereço de memória
	   HLT	// Finaliza a execução do programa


//Lembrar de colocar os valores de 8000 e 8002 em complemento, o 8004 ja vai ser transformado