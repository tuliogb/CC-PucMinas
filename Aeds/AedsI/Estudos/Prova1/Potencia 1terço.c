#include <stdio.h>
#include <stdlib.h>

int main()
{
    float result,div;
    int i,num=1,n;

    printf("Quantos termos?");
    scanf("%i",&n);

    for(i>0;i<n;i++){
        num=num*3;
        div=num;
        result=1/div;
        printf("1/%i\n",num);
        printf("Resultado:%f\n",result);

    }
    return 0;
}
