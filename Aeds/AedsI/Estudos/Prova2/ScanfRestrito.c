#include <stdio.h>
#include <stdlib.h>

void LerNome(){

    char nome[30];

    printf("\nQUAL O SEU NOME:");
    scanf("%30[^\n]",nome); // 30 é o maximo que ela pode ler;

    printf("%s",nome);//o restante que exede 30 caracteres sera perdido e nao alocado;

}
int main()
{
    LerNome();
    return 0;
}
