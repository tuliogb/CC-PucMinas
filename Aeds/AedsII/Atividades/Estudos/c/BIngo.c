/*
Descrição

Bingo de São João será um carro zero-quilômetro. Todo mundo quer ser o primeiro a completar sua cartela, claro. São N cartelas identificadas de 1 até N que contêm, cada uma, K números distintos entre os números naturais de 1 até U, para K < U. Um número, claro, pode aparecer em mais de uma cartela e duas cartelas podem até ser iguais, ter o mesmo conjunto de números. Justamente por isso, veja que pode acontecer empate com mais de uma cartela sendo completada no mesmo instante.

Neste problema, serão dados na entrada os conjuntos de números de todas as cartelas e a sequência de números sorteados, que será uma permutação dos naturais de 1 até U. Seu programa deve determinar qual ou quais cartelas vão ser completadas primeiro e ganhar o carro.

Por exemplo, para N = 4, K = 5 e U = 10, com as cartelas dadas pela tabela abaixo, se a sequência de números sorteados for [7, 3, 5, 2, 6, 1, 9, 10, 4, 8], então haverá uma cartela vencedora, a número 3.

número da cartela 					
1 	3 	10 	8 	7 	2
2 	4 	1 	7 	10 	9
3 	9 	1 	5 	3 	6
4 	6 	8 	1 	5 	7

Entrada
A primeira linha da entrada contém três inteiros N, K e **Uv representando respectivamente o número de cartelas, quantos números cada cartela contém e o maior natural que pode ocorrer numa cartela. As N linhas seguintes contêm, cada uma, K inteiros distintos Ci, para 1 ≤ i ≤ K, representando o conjunto de números de cada cartela, da cartela 1 até a **N. A última linha da entrada contém U inteiros indicando a sequência de números sorteados, uma permutação dos naturais entre 1 e U.

Saída
Seu programa deve imprimir uma linha contendo os números identificadores das cartelas vencedoras do carro, em ordem crescente.
*/


/*
    Explicacao Walisson: Tamanho de linhas da tabela X Quantidade de numeros em cada linha X Quantidade de numeros sortiado; 
    4 entradas com os numeros porque sao 4 linhas;
    entrada com os numeros sortiados;
*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h> 

int NLIN = 0, NCOL = 0, sortiados = 0;

void dadosTabela();
int** setaTabela();
int* sorteados();
void bingo(int* sort, int** tabela);
int resultado(int** matriz);



void dadosTabela(){
	char entrada[20];
	fgets(entrada, sizeof(entrada), stdin);					// Limpa o buffer(std.in) >>> while (getchar() != '\n');
	
	char *token = strtok(entrada, " ");
	NLIN = atoi(token);
	token = strtok(NULL, " ");
	NCOL = atoi(token);
	token = strtok(NULL, " ");
	sortiados = atoi(token); 	
}

int** setaTabela() {
    int** matriz = (int**)malloc(NLIN * sizeof(int*));		// E alocado 4 espacos >> 1 espaco aponta pra o primeiro elemento de um array (primeira linha)			
    for (int l = 0; l < NLIN; l++) {
        matriz[l] = (int*)malloc(NCOL * sizeof(int));		// A funcao malloc retorna o endereco do bloco alocado, entao o recebimento tem que ser ponteiro
        char entrada[20];
        fgets(entrada, sizeof(entrada), stdin);
        char* token = strtok(entrada, " ");
        matriz[l][0] = atoi(token);
        for (int c = 1; c < NCOL; c++) {
            token = strtok(NULL, " ");
            matriz[l][c] = atoi(token);
        }
    }
    return matriz;
}

int* sorteados(){
	int* sort = (int*)malloc(sortiados * sizeof(int));
	char entrada[25];
	fgets(entrada, sizeof(entrada), stdin);				
	
	char *token = strtok(entrada, " ");                             /* 'strtok modifica a string original, substituindo os delimitadores por terminadores nulos.*/
	sort[0] = atoi(token);
	
	for (int i=1;i<sortiados;i++){
		token = strtok(NULL, " ");
		sort[i] = atoi(token);
	}
	
	return sort;
}

void bingo(int* sort, int** tabela) {
    for (int l = 0; l < NLIN; l++) {
        for (int c = 0; c < NCOL; c++) {
            for (int s = 0; s < sortiados; s++) {
                if (tabela[l][c] == sort[s]) {
                    tabela[l][c] = 0; 
                }
            }
        }
    }
}

int resultado(int** matriz){
    int cont = 0, resp = 0;
	for (int l = 0; l < NLIN; l++) {
        for (int c = 0; c < NCOL; c++) {
            if(matriz[l][c] == 0){
				cont++;
				if(cont == NCOL){				// Se der isso é porque todos os valores sao nulos.
					resp = l;
				}
			}
        }
		cont=0;
    }	

	return resp;
}


void imprimirMatriz(int** matriz) {
    for (int i = 0; i < NLIN; i++) {
        for (int j = 0; j < NCOL; j++) {
            printf("%d ", matriz[i][j]);
        }
        printf("\n");
    }
}

void liberaMemoria(int** matriz, int* sort) {
    for (int l = 0; l < NLIN; l++) {
        free(matriz[l]);
    }
    free(matriz);
    free(sort);
}

	

int main(){
	dadosTabela();
	int** matriz = setaTabela();
	int* sort = sorteados();
	bingo(sort,matriz);
	printf("%i",resultado(matriz));
	liberaMemoria(matriz,sort);
	//imprimirMatriz(matriz);
}




/*
Quando você chama a função strtok pela primeira vez com a string original como o primeiro argumento, a função encontra o primeiro token na string e guarda a posição do início desse token. Essa informação é armazenada em uma variável estática interna à biblioteca C usada pela função strtok. Essa variável mantém a posição do último caractere que a função encontrou como um token.

Nas chamadas subsequentes à função strtok com NULL como o primeiro argumento, a função utiliza essa variável estática interna para saber onde parou e continua a partir dessa posição. Ela examina a string a partir desse ponto em busca do próximo token com base nos delimitadores especificados.

A variável interna é mantida entre chamadas à função strtok, permitindo que você divida a string em tokens de maneira incremental sem precisar rastrear a posição manualmente. Isso é uma característica útil para processar uma string token por token.
*/