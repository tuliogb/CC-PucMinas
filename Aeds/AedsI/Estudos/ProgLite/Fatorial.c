#include <stdio.h>
#include <stdlib.h>


int fatorial(int t){

    int fat;

    if(t==1){
        fat=1;
    }else{
        fat=t*fatorial(t-1);
    }
    return fat;

}


int ateN(int n){

    int a;

    if(n>0){
        a=n+ateN(n-1);
    }

    return a;
}


int main()
{
    int n=3;
    int t=5;

    printf("\n%i",ateN(n));
    printf("\n%i",fatorial(t));

    return 0;
}
