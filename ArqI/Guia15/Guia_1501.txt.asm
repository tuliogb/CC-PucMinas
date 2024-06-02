	   LXI H,8000	// Inicia o ponteiro H apontando para o endereço de memória 8000
	   MOV A,M	// Move o conteúdo do endereço de memória apontado por HL para o registrador A (acumulador)
	   INX H	// Incrementa o ponteiro H para apontar para o próximo byte de memória, no caso 8001
	   ADD M	// Adiciona o conteúdo do endereço de memória apontado por HL ao registrador A
	   MOV B,A	// Move o resultado da adição para o registrador B
	   LXI H,8002	// Carrega o endereço de memória 8002 no ponteiro H
	   MOV A,M	// Move o número que você deseja subtrair do endereço de memória 8002 para o registrador A
	   SUB B	// Subtrai o conteúdo do registrador B (resultado da adição) do registrador A
	   STA 8003	// Armazena o resultado da subtração no endereço de memória 8003
	   HLT	// Termina a execução do programa
