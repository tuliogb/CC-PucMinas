#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n,i,num=0,num2=1,result=0;

    printf("Sequencia Fibonacci\n");
    printf("Insira o numero de termos:");
    scanf("%i",&n);

    for(i>2;i<n-1;i++){
        result=num+num2;
        printf("%i, ",result);
        num=num2;
        num2=result;
    }
    return 0;
}
