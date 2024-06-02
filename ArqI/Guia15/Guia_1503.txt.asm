
START:	   MVI A,15	// 15 em BCD)
	   MOV B,A	// Salvar em B
	   MVI A,12	// 12 em BCD)
	   ADD B	// Adicionar 12 ao 15 (B)
	   MOV B,A	// Salvar a soma de 15 e 12 em B
	   MVI A,14	// 14 em BCD
	   ADD B	// Adicionar 14 à soma de 15 e 12 (B)
// Verificar se a soma excede 9
	   CPI 10	// Comparar com 10 (excede 9?)
	   JNC AJUSTAR	// Se a soma for maior ou igual a 10, ajustar
// Se não exceder 9, armazenar o resultado diretamente
	   MOV M,A	// Armazenar o resultado na memória
	   JMP FIM	// Pular para o fim do programa

AJUSTAR:	   DAA	// Ajustar o próximo bit para converter a soma para BCD
// Armazenar o resultado ajustado na memória
	   MOV M,A	// Armazenar o resultado na memória

FIM:	   HLT	// Parar a execução do programa
