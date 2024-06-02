
START:	   MVI D,00	// Inicializa o acumulador do produto com 0
	   LDA 8000	// Carrega o primeiro número de 8000H
	   MOV B,A	// Move o primeiro número para B
	   LDA 8001	// Carrega o segundo número de 8001H
	   MOV C,A	// Move o segundo número para C

MUL_LOOP:	   MOV A,C	// Move o conteúdo de C para A
	   CPI 00	// Compara A com 0
	   JZ END_LOOP	// Se A for 0, pula para END_LOOP
	   MOV A,D	// Move o acumulador do produto para A
	   ADD B	// Adiciona o valor de B ao acumulador do produto
	   MOV D,A	// Move o resultado de volta para D
	   DCR C	// Decrementa C (contador)
	   JMP MUL_LOOP	// Repete o loop

END_LOOP:	   MOV A,D	// Move o resultado final para A
	   STA 8002	// Armazena o resultado em 8002H
	   HLT	// Termina o programa


//Lembrar de colocar os valores de 8000 e 8002 