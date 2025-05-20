#include <iostream>
#include <queue>
using namespace std;

const int numVertice = 10;

int matriz1[numVertice][numVertice] = {
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

int matriz2[numVertice][numVertice] = {
    //   a, b, c, d, e, f, g, h, i, j
        {0, 1, 0, 0, 1, 0, 0, 0, 1, 0}, // a -> b, e, i
        {1, 0, 1, 0, 0, 1, 0, 0, 0, 0}, // b -> a, c, f
        {0, 1, 0, 1, 0, 0, 1, 0, 0, 0}, // c -> b, d, g
        {0, 0, 1, 0, 1, 0, 0, 1, 0, 0}, // d -> c, e, h
        {1, 0, 0, 1, 0, 1, 0, 0, 0, 1}, // e -> a, d, f
        {0, 1, 0, 0, 1, 0, 1, 0, 0, 0}, // f -> b, e, g
        {0, 0, 1, 0, 0, 1, 0, 1, 0, 0}, // g -> c, f, h
        {0, 0, 0, 1, 0, 0, 1, 0, 1, 0}, // h -> d, g, i
        {1, 0, 0, 0, 0, 0, 0, 1, 0, 1}, // i -> a, h, j
        {0, 0, 0, 0, 1, 0, 0, 0, 1, 0}  // j -> e, i, (volta para manter regularidade)
    };
    

    int matriz3[numVertice][numVertice] = {
        //   a, b, c, d, e, f, g, h, i, j
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0}, // a → b, e, i
            {0, 0, 1, 0, 0, 1, 0, 0, 0, 1}, // b → c, f, j
            {1, 0, 0, 1, 0, 0, 1, 0, 0, 0}, // c → a, d, g
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 0}, // d → b, e, h
            {0, 0, 1, 0, 0, 1, 0, 0, 1, 0}, // e → c, f, i
            {0, 0, 0, 1, 0, 0, 1, 0, 0, 1}, // f → d, g, j
            {1, 0, 0, 0, 1, 0, 0, 1, 0, 0}, // g → a, e, h
            {0, 1, 0, 0, 0, 1, 0, 0, 1, 0}, // h → b, f, i
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 1}, // i → c, g, j
            {1, 0, 0, 1, 0, 0, 0, 1, 0, 0}  // j → a, d, h
        };
        
    

int somas[numVertice];
queue<int> fila;

bool eRegularND(int matriz[numVertice][numVertice])           // nao direcionado
{
    int soma=0;
    for(int i=0; i<numVertice; i++)
    {
        for(int j=0; j<numVertice; j++) soma+=matriz[i][j];
        somas[i]=soma;
        soma=0;
    }

    for(int i=1; i<numVertice; i++) if(somas[i-1]!=somas[i]) return false;
    return true;
}

bool eRegularD(int matriz[numVertice][numVertice])           // direcionado
{
    int soma=0;
    for(int i=0; i<numVertice; i++)
    {
        for(int j=0; j<numVertice; j++) soma+=matriz[i][j];
        somas[i]=soma;
        soma=0;
        for(int j=0; j<numVertice; j++) soma+=matriz[j][i];
        if(somas[i]!=soma) printf("erro: %i", i);
        soma=0;
    }

    for(int i=1; i<numVertice; i++) if(somas[i-1]!=somas[i]) return false;
    return true;
}


int main()
{
    if(eRegularD(matriz3)) printf("regular\n");
    else printf("n regular\n");
    return 0;
}