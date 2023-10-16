#include <stdio.h>
#include <stdlib.h>

int main()
{
    int num;
    printf("NUMERO FATORIAL:\n");
    scanf("%i",&num);

    do{
        printf("%i\n",num);
        num--;
    }while(num>=0);

    return 0;
}
