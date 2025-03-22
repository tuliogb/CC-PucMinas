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
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // i -> j
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // j
};


int visitados[numVertice];
queue<int> fila;


void walk(int lin)                            // Nao preciso preocupar se ja foi visitado (se tiver ciclo F)
{
    for(int i=0; i<numVertice; i++) if(matriz[lin][i]==1){ fila.push(i); printf("Empilho: %i\n", i); }

    while(!fila.empty())
    {
        int num = fila.front();
        printf("Removo: %i \n", num);
        fila.pop();
        walk(num);
    }
}

void path(int lin)                            // Preciso preocupar se ja foi visitado (se tiver ciclo F)
{
    for(int i=0; i<numVertice; i++) if(matriz[lin][i]==1 && visitados[i]!=1){ fila.push(i); printf("Empilho: %i\n", i); visitados[i]=1; }

    while(!fila.empty())
    {
        int num = fila.front();
        printf("Removo: %i \n", num);
        fila.pop();
        path(num);
    }
}



int main()
{
    path(0);
    return 0;
}