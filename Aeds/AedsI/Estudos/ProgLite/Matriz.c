#include <stdio.h>
#include <stdlib.h>

int main() {
    int N, M;
    scanf("%d %d", &N, &M);
    getchar(); // Ler o caractere de quebra de linha após a leitura de M

    char matriz[N][M];
    char nova_matriz[N][M];

    // Ler a matriz de entrada
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            matriz[i][j] = getchar();
        }
        getchar(); // Ler o caractere de quebra de linha após a leitura da linha i
    }

    // Inicializar a nova matriz com pontos
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            nova_matriz[i][j] = '.';
        }
    }

    // Percorrer a matriz e atualizar as posições molhadas
    for (int i = 1; i < N; i += 2) {
        for (int j = 1; j < M - 1; j++) {
            if (matriz[i-1][j] == 'o' || (matriz[i][j-1] == 'o' && matriz[i+1][j-1] == '#') || (matriz[i][j+1] == 'o' && matriz[i+1][j+1] == '#')) {
                nova_matriz[i][j] = 'o';
            }
        }
    }

    // Imprimir a nova matriz
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            printf("%c", nova_matriz[i][j]);
        }
        printf("\n");
    }

    return 0;
}
