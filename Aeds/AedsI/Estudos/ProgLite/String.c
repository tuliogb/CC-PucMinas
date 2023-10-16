#include <stdio.h>
#include <stdlib.h>


int x;


void pegaString(){

    char frase[20]={};

    printf("\nSEU NOME: ");
    fgets(frase,20,stdin);

    printf("%s",frase);
}


int main()
{

    int *p=7;


    printf("%p",&p);

    return 0;
}
