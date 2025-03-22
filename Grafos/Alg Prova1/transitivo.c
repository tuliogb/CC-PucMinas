#include <iostream>
#include <stack>
using namespace std;

const int numVertice = 10;

int matriz[numVertice][numVertice] = {
    //  a, b, c, d, e, f, g, h, i, j
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


int eixoTransitivo[numVertice];
stack<int> pilha;


void buscaTransitivoDireto(int vertice)
{
    for (int i=0; i<numVertice; i++) 
    {
        if(matriz[vertice][i]==1)
        {
            if(eixoTransitivo[i]!=1)
            {
                eixoTransitivo[i]=1;
                pilha.push(i);
            }
        } 
    }

    if(vertice<numVertice)
    {
        while(!pilha.empty())
        {
            int proximo = pilha.top();
            printf("%i ",proximo);
            pilha.pop();
            buscaTransitivoDireto(proximo);
        } 
    }
}

void buscaTransitivoIndireto(int vertice)
{
    for (int i=0; i<numVertice; i++) 
    {
        if(matriz[i][vertice]==1)
        {
            if(eixoTransitivo[i]!=1)
            {
                eixoTransitivo[i]=1;
                pilha.push(i);
            }
        } 
    }

    if(vertice<numVertice)
    {
        while(!pilha.empty())
        {
            int proximo = pilha.top();
            printf("%i ",proximo);
            pilha.pop();
            buscaTransitivoIndireto(proximo);
        } 
    }
}

void setaArray()
{
    for (int i=0; i<numVertice; i++) eixoTransitivo[i]=0;
}



int main() {

    setaArray();
    buscaTransitivoDireto(2);
    printf("\n");
    setaArray();
    buscaTransitivoIndireto(1);

    return 0;
}