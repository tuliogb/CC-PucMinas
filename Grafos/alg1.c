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

int visitados[6];
int ciclos=0, maxLin=5, maxCol=5, visita=0;

bool possoIr(int linha)
{
    return (linha > visitados[visita]) ? true : false;
}

void gerarCiclos(int lin, int col, int procurado)
{
    if(col<maxCol && lin<maxLin)
    {
        if(matriz[lin][col]==1)
        {
            visitados[visita]=col;
            if(possoIr(col)) gerarCiclos(col,0,procurado);
            else gerarCiclos(lin,col+1,procurado);
        }
        else gerarCiclos(lin,col+1,procurado);
    }
}

int main()
{
    gerarCiclos(0,0,0);
}

//printf("[%i][%i]\n",lin,col);