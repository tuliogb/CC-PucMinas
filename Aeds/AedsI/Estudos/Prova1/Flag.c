#include <stdio.h>
#include <stdlib.h>

int const flag=0;

int main()
{
    int soma=0,i=0,num,maior=0;


    do{
        printf("Digite numeros:\n");
        scanf("%i",&num);

        soma=soma+num;
        if(num>maior) maior=num;

        i++;

    }while(num!=flag);

    printf("Programa Finalizado");

    soma=soma/10;
    printf("\nMedia:%i\n",soma);
    printf("Maior:%i\n",maior);

    return 0;
}
