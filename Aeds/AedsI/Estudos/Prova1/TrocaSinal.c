#include <stdio.h>
#include <stdlib.h>

int main()
{
    float num=0;
    int i=0;
    printf("Modulo:\n");
    scanf("%f",&num);


    if(num>0){
        printf("%f",num);
    }else{
        i=num*-1;
        printf("%i",i);
    }

    return 0;
}
