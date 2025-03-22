#include <iostream>
#include <queue>
using namespace std;

const int numVertice = 10;
int matriz[numVertice][numVertice] = {
 //  a, b, c, d, e, f, g, h, i, j
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // a -> b
    {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}, // b -> c, e
    {0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, // c -> d, f
    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // d -> e
    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // e -> f
    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // f -> g
    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // g -> h
    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // h -> i
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // i -> j
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // j
};

/*
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
*/

int cores[numVertice];
queue<int> fila;


bool isBipartido(int lin, bool resp, int cor)
{
    for(int i=0; i<numVertice && resp; i++)
    { 
        if(matriz[lin][i]==1) 
        {
            if(cores[i]==0)
            {
                cores[i]=cores[lin]==1 ? 2:1;
                fila.push(i);
            }
            else if(cores[i]==cores[lin]) resp = false;
        }
    }

    while(!fila.empty() && resp)
    {
        int num = fila.front();
        fila.pop();
        resp = isBipartido(num, resp, cores[num]);
    }
    return resp;
} 

void setaArray()
{
    for(int i=0; i<numVertice; i++) cores[i]=0;
}

int main() 
{
    setaArray();
    cores[0]=1;
    printf(isBipartido(0,true,2) ? "Bipartido" : "Nao Bipartido");
    return 0;
}