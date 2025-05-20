#include <iostream>
#include <queue>
using namespace std;

queue<int> fila;
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


bool isBase(int col)
{
    bool base = true;
    for(int i=0; i<numVertice && base==true; i++)
    { 
        if(matriz[i][col]==1) base=false;
    }

    return base;
}

bool isAntiBase(int lin)
{
    bool base = true;
    for(int i=0; i<numVertice && base==true; i++)
    { 
        if(matriz[lin][i]==1) base=false;
    }

    return base;
}

void encontraBaseAntiBase()
{
    for(int i=0; i<numVertice; i++)
    { 
        if(isBase(i)) printf("%i base\n", i);
        if(isAntiBase(i)) printf("%i anti base\n", i);
    }
}



int main() {
    encontraBaseAntiBase();
    return 0;
}


/*
Funciona para DAG
O vertice com eixo transitivo inverso com somente ele mesmo é uma base
*/