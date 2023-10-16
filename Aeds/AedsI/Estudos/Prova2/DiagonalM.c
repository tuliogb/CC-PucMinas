#include <stdio.h>
#include <stdlib.h>

int nlin;
int ncol;

void tamanhoM(){

    printf("\nNUMERO DE LINHAS:");
    scanf("%i",&nlin);

    printf("\nNUMERO DE COLUNAS:");
    scanf("%i",&ncol);

}


void valoresM(int matriz[][ncol]){

    for(int c=0;c<ncol;c++){

        for(int l=0;l<nlin;l++){
            printf("\nVALOR:");
            scanf("%i",&matriz[l][c]);
        }
    }

}

void superiorDiagonal(int matriz[][ncol]){


    for(int l=0;l<nlin;l++){

        for(int c=l+1;c<ncol;c++){
            printf("%i, ",matriz[l][c]);
        }//c

    }//l

}
int main()
{
    tamanhoM();

    int matriz[nlin][ncol];

    valoresM(matriz);
    superiorDiagonal(matriz);

    return 0;
}
