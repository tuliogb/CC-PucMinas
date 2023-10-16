#include <stdio.h>
#include <stdlib.h>

int main(){

    int num1,num2,cres;
    printf("Informe dois numeros!\n");
    scanf("%i%i",&num1,&num2);

    cres=num1;

    printf("Sequencia cres:\n");
    do{
        printf("%i\n",num1);
        num1++;
    }while(num1<=num2);

    printf("Sequencia decres:\n");

    do{
        printf("%i\n",num2);
        num2--;
    }while(num2>=cres);
    return 0;
}
