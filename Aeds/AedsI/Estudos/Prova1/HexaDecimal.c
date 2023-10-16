#include <stdio.h>
#include <stdlib.h>

int main()
{
    int num,quociente=0,resto=0;

    printf("Insira o valore em decimal!\n");
    scanf("%i",&num);

    do{
        quociente=num/16;
        resto=num%16;

        printf("quociente:%i\n",quociente);
        printf("resto:%i\n",resto);

        num=quociente;


    }while(quociente>0);


    return 0;
}