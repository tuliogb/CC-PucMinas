#include <stdio.h>
#include <stdlib.h>

void apresentacao() {
    printf("\n--TIPOS DE TRIANGULOS--\n");
}

int escolha() {
    int opcao;
    printf("\nCATALOGO DE OPCOES:\n1-Equilatero.\n2-Isoscele.\n3-Escaleno.\n");
    printf("\nPORTANTO ESCREVA UM NUMERO EQUIVALENTE AO CATALOGO:\n");
    scanf("%i", &opcao);
    return opcao;
}

int leialado(int x) {
    int lado;
    do {
        printf("DIGITE O LADO %d:\n", x);
        scanf("%i", &lado);
        if (lado <= 0) {
            printf("VALOR NEGATIVO OU ZERO NAO E VALIDO\n");
        }
    } while (lado <= 0);
    return lado;
}

int verificacao(int tipooff) {
    if (tipooff >= 1 && tipooff <= 3) {
        return tipooff;
    } else {
        return 0; 
    }
}

char trueorfalse(int l1, int l2, int l3, int tipoon) {
    if (l1 > 0 && l2 > 0 && l3 > 0) {
        if (tipoon == 1) {
            if (l1 == l2 && l1 == l3) {
                return 'V';
            }
        } else if (tipoon == 2) {
            if (l1 == l2 || l1 == l3 || l2 == l3) {
                return 'V';
            }
        } else if (tipoon == 3) {
            if (l1 != l2 && l1 != l3 && l2 != l3) {
                return 'V';
            }
        }
    }
    return 'F';
}

int main() {
    int tipooff, tipoon, l1, l2, l3, x = 1;
    char resposta;

    apresentacao();
    tipooff = escolha();

    l1 = leialado(x);
    x++;
    l2 = leialado(x);
    x++;
    l3 = leialado(x);

    tipoon = verificacao(tipooff);

    resposta = trueorfalse(l1, l2, l3, tipoon);

    if (resposta == 'V') {
        printf("\nOs lados formam um triângulo do tipo escolhido.\n");
    } else {
        printf("\nOs lados não formam um triângulo do tipo escolhido.\n");
    }

    return 0;
}
