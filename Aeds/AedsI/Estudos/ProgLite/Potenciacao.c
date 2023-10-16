#include <stdio.h>
#include <stdlib.h>


int potenciacao(int b,int n){

    int r;

    if(n==0)r=1;
    else{
        r=b*potenciacao(b,n-1);
    }

    return r;
}


int main()
{
    int b=2,n=4,x=0;

    x=potenciacao(b,n);

    printf("%i",x);

    return 0;
}
