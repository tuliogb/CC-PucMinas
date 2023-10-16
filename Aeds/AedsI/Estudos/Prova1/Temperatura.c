#include <stdio.h>
#include <stdlib.h>

int main()
{
    int tempf,tempc=0;
    printf("Insira a temperatura:\n");
    scanf("%i",&tempf);

    tempc = tempf-32;
    tempc = tempc*5;
    tempc = tempc/9;


    printf("Valor em Celsius:%i",tempc);

    return 0;
}
