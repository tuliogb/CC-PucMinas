#include <iostream>
#include <queue>
using namespace std;

const int numVertice = 10;


int matriz[numVertice][numVertice] = {
//   a, b, c, d, e, f, g, h, i, j
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // a -> b
    {0, 0, 1, 1, 1, 0, 0, 0, 0, 0}, // b -> c, d, e
    {0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, // c -> d, e, f
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // d -> e
    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // e -> f
    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // f -> g
    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // g -> h
    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // h -> i
    {0, 0, 0, 1, 0, 0, 0, 0, 0, 1}, // i -> j
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // j
};


int visitados[numVertice];
queue<int> fila;

void identificaPendenteND()                           // nao direcionado
{
    int soma=0;
    for(int i=0; i<numVertice; i++)
    {
        for(int j=0; j<numVertice; j++) soma+=matriz[i][j]; 
        if(soma==1) printf("%i pendente\n", i);
        soma=0;
    }
}


void identificaPendenteD()                           // direcionado
{
    int soma=0;
    for(int i=0; i<numVertice; i++)
    {
        for(int j=0; j<numVertice; j++) soma+=matriz[i][j]; 
        if(soma==0)
        {
            for(int k=0; k<numVertice; k++) soma+=matriz[k][i];
            if(soma==1) printf("%i pendente\n", i);
        }
        soma=0;
    }
}

int main()
{
    identificaPendenteD();
    return 0;
}