#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *arq =fopen("teste.txt","w");

    int valores;

    for(int x=10;x<15;x++){
        fprintf(arq,"%i\n",x);
    }

    while(fscanf(arq,"%i",valores)!=feof(aqr){
        printf("%i",valores);
    }

    fclose(arq);


    return 0;
}
