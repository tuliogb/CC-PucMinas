#include <stdio.h>
#include <string.h>
#include <stdbool.h>

 /*
 * Trabalho Pratico 1 - Exercicio 2
 * Tulio Gomes Braga
 * 802512
 */


bool palindromo(char *input){
    bool resp = true;
    int tam = strlen(input)-2;      // Menos 2 porque tem o \n do fgets e considerando casa fisica;

    for(int i=0, j=tam; i<j; i++,j--){
        if(input[i]!=input[j]){
            resp = false;
            i=tam;
        }
    }

    return resp;

    /*  Curiosidade:

        Se nao quiser usar strlen podemos fazer  size_t tamanho =  sizeof(input) / sizeof(input[0]);       
        ->> size_t porque o numero pode mais que 32bits (somente positivos)
    */
}

void start(){
    char input[100];
    fgets(input, sizeof(input), stdin);

    while(strcmp(input, "FIM\n")!=0){
        printf("%s\n", palindromo(input) ? "SIM" : "NAO");
        fgets(input, sizeof(input), stdin);
    }
}



int main(){
    start();
}