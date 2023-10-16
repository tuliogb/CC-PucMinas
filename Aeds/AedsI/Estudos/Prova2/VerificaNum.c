#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool TouF(int valores[],int tamanho){

    int r=1;

    for(int i=0;i<tamanho;i++){

        if(valores[i]>100 || valores[i]<0){
            r=0;
            i=tamanho;
        }

    }

    return r;
}

int main()
{
    int valores[30]={0,10,20,30,40,50,60,70,80,99,100};

    int resp=TouF(valores,30);

    printf("%i",resp);
    return 0;
}
