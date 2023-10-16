#include <stdio.h>
#include <stdlib.h>

int main()
{

    int cont=0,result,fixo=9;

    printf("TABELA DE TABOADA DO 9:\n");

    do{
        cont++;
        result=cont*fixo;
        printf("%i*%i=%i\n",cont,fixo,result);
    }

    while(cont<=9);

    return 0;
}
