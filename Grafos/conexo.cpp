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
    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // i -> j
    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // j
};


int visitados[numVertice];
queue<int> fila;


void setaNaoDirecionado()
{
    for(int i=0; i<numVertice; i++)
    {
        for(int j=0; j<numVertice; j++) if(matriz[i][j]==1) matriz[j][i]=1; 
    }
}

void path(int lin)                            // Preciso preocupar se ja foi visitado (se tiver ciclo F)
{
    for(int i=0; i<numVertice; i++) if(matriz[lin][i]==1 && visitados[i]!=1){ fila.push(i); visitados[i]=1; }

    while(!fila.empty())
    {
        int num = fila.front();
        fila.pop();
        path(num);
    }
}

bool eConexo()                                  // Se for direcionado é descomentar essa linha abaixo
{
    setaNaoDirecionado();
    visitados[0]=1;
    path(0);
    for(int i=0; i<numVertice; i++) if(visitados[i]!=1) return false;
    return true;
}



int main()
{
    if(eConexo()) printf("conexo\n");
    else printf("n conexo\n");
    return 0;
}