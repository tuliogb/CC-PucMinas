#include <stdio.h>
#include <stdbool.h>


int matriz[6][6] = {
//   a b c d e f
    {0,1,0,1,1,0},  //a
    {1,0,1,1,1,0},  //b
    {0,1,0,1,1,1},  //c
    {1,1,1,0,0,1},  //d
    {1,1,1,0,0,1},  //e
    {0,0,1,1,1,0}   //f
};


/*
int matriz[4][4] = {
    {0, 1, 1, 1},  // vértice A
    {1, 0, 1, 1},  // vértice B
    {1, 1, 0, 1},  // vértice C
    {1, 1, 1, 0}   // vértice D
};
*/


int visitados[6];
int ciclos=0, maxLin=6, maxCol=6, qtdVisitados=0;



bool possoIr(int linha)
{
    if(visitados[linha] != 1)   // se eu nao tiver visitado
    {
        visitados[linha] = 1;
        qtdVisitados++;
        return true;
    }
    return false;
}

void verificaCiclo(int linha, int procurado)
{
    if(matriz[linha][procurado]==1 && qtdVisitados>2)
    {
        printf("Cliclo marcado\n");
        ciclos++;
    } 
}

void gerarCiclos(int lin, int col, int procurado)
{
    if(col<maxCol && lin<maxLin)
    {
        if(matriz[lin][col]==1)
        {
            if(possoIr(col))
            { 
                printf("[%i][%i]\n",lin,col);
                verificaCiclo(lin, procurado);
                gerarCiclos(col,0,procurado);
                visitados[col]=0;
                qtdVisitados--;
            }
            gerarCiclos(lin,col+1,procurado);
        }
        else gerarCiclos(lin,col+1,procurado);
    }
}

void removeVertice(int vertice)
{
    for (int i=0; i<maxLin; i++)
    { 
        matriz[i][vertice]=0;
        matriz[vertice][i]=0;
    }
}

void setaVisitados(int i)
{
    for (int i=0; i<maxCol; i++) visitados[i]=0;
    visitados[i] = 1;
    qtdVisitados++;
}



int main()
{
    for (int i=0; i<maxLin; i++)
    {
        setaVisitados(i);
        gerarCiclos(i,i,i);
        printf("\n");
        removeVertice(i);
    }
    printf("Total de ciclos encontrados: %d\n", ciclos/2);
    return 0;
}