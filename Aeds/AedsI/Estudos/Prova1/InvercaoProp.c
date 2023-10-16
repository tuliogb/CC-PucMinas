#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i,a=10,b=100,n;


    printf("numero N:");
    scanf("%i",&n);


    for(i>0;i<n;i++){

        if(a%2==0){
            printf("%i/%i\n",a,b);
        }
        else{
            printf("%i/%i\n",b,a);
        }

        a++;
        b--;
    }
    return 0;
}
